package com.jobseeker.hrms.organization.service.basic.workplacement.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.workplacement.WorkplacementOrgOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Workplacement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("deleteWorkplacement")
public class DeleteWorkplacement {

    private final WorkplacementOrgOrgRepository workplacementOrgRepository;
    private final VacancyOrgRepository vacancyRepository;
    private final EmployeeOrgRepository employeeRepository;

    public String execute(String oid) {
        Workplacement workplacement = workplacementOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_RECRUITMENT_STAGE_WITH_ID_FOUND.getMsg()));
        workplacement.setStatus(StatusData.DELETED);
        workplacement.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.isExist("workplacementType._id", oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete job level because it's used in vacancy data.");
        }
        //</editor-fold>

        workplacementOrgRepository.save(workplacement);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
