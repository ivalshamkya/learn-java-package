package com.jobseeker.hrms.organization.service.sms.branch.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import com.jobseeker.hrms.organization.repositories.sms.employee.EmployeeOrgSMSRepository;
import com.jobseeker.hrms.organization.repositories.sms.organization.branch.BranchSMSRepository;
import com.jobseeker.hrms.organization.repositories.sms.vacancy.VacancyOrgSMSRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jobseeker.employee.repositories.EmployeeRepository;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.sms.BranchSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("SMSDeleteBranch")
public class SMSDeleteBranch {

    private final BranchSMSRepository branchRepository;
    private final VacancyOrgSMSRepository vacancyRepository;
    private final EmployeeOrgSMSRepository employeeRepository;

    public String execute(String oid) {
        BranchSMS branchSMS = branchRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
        branchSMS.setStatus(StatusData.DELETED);
        branchSMS.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.isExist("branch._id", new ObjectId(oid));
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete branch because it's used in vacancy data.");
        }
        //</editor-fold>

        //<editor-fold desc="Check if used in Employee">
        boolean existInEmployee = employeeRepository.isExist("smsEmployment.branch._id", oid);
        if (existInEmployee) {
            throw new NoSuchElementException("Can't delete branch because it's used in employee data.");
        }
        //</editor-fold>

        branchRepository.save(branchSMS);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
