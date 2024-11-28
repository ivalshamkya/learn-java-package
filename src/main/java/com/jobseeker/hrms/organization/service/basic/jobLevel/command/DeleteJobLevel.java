package com.jobseeker.hrms.organization.service.basic.jobLevel.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel.JobLevelOrgOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.JobLevel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("deleteJobLevel")
public class DeleteJobLevel {

    private final JobLevelOrgOrgRepository jobLevelOrgRepository;
    private final VacancyOrgRepository vacancyRepository;
    private final EmployeeOrgRepository employeeRepository;

    public String execute(String oid) {
        JobLevel jobLevel = jobLevelOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
        jobLevel.setStatus(StatusData.DELETED);
        jobLevel.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.isExist("jobLevel._id", oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete job level because it's used in vacancy data.");
        }
        //</editor-fold>

        //<editor-fold desc="Check if used in Employee">
        boolean existInEmployee = employeeRepository.isExist("employment.jobLevel._id", oid);
        if (existInEmployee) {
            throw new NoSuchElementException("Can't delete job level because it's used in employee data.");
        }
        //</editor-fold>

        jobLevelOrgRepository.save(jobLevel);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
