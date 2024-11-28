package com.jobseeker.hrms.organization.service.lws.branch.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.branch.BranchOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.lawson.BranchLawson;
import org.jobseeker.vacancy.lawson.repositories.LawsonVacancyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonDeleteBranch")
public class LawsonDeleteBranch {

    private final BranchOrgLWSRepository branchRepository;
    private final VacancyOrgLWSRepository vacancyRepository;
    private final EmployeeOrgLWSRepository employeeRepository;

    public String execute(String oid) {
        BranchLawson branch = branchRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
        branch.setStatus(StatusData.DELETED);
        branch.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">
        boolean existInVacancy = vacancyRepository.existsByBranchId(oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete branch because it's used in vacancy data.");
        }
        //</editor-fold>

        //<editor-fold desc="Check if used in Employee">
        boolean existInEmployee = employeeRepository.existsByBranchId(oid);
        if (existInEmployee) {
            throw new NoSuchElementException("Can't delete branch because it's used in employee data.");
        }
        //</editor-fold>

        branchRepository.save(branch);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
