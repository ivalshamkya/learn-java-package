package com.jobseeker.hrms.organization.service.lws.jobType.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobType.JobTypeOrgOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.JobType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonDeleteJobType")
public class LawsonDeleteJobType {

    private final JobTypeOrgOrgRepository jobTypeOrgRepository;
    private final VacancyOrgLWSRepository vacancyRepository;
    private final EmployeeOrgLWSRepository employeeRepository;

    public String execute(String oid) {
        JobType jobType = jobTypeOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
        jobType.setStatus(StatusData.DELETED);
        jobType.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.existsByJobTypeId(oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete job type because it's used in vacancy data.");
        }
        //</editor-fold>

        jobTypeOrgRepository.save(jobType);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
