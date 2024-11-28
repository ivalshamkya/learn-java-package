package com.jobseeker.hrms.organization.service.basic.branch.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Branch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createBranch")
public class DeleteBranch {

    private final BranchOrgRepository branchRepository;
    private final VacancyOrgRepository vacancyRepository;
    private final EmployeeOrgRepository employeeRepository;

    public String execute(String oid) {
        Branch branch = branchRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
        branch.setStatus(StatusData.DELETED);
        branch.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.isExist("branch._id", oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete branch because it's used in vacancy data.");
        }
        //</editor-fold>

        //<editor-fold desc="Check if used in Employee">
        boolean existInEmployee = employeeRepository.isExist("employment.branch._id", oid);
        if (existInEmployee) {
            throw new NoSuchElementException("Can't delete branch because it's used in employee data.");
        }
        //</editor-fold>

        branchRepository.save(branch);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
