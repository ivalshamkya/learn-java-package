package com.jobseeker.hrms.organization.service.basic.jobType.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobType.JobTypeOrgOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
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
@Qualifier("deleteJobType")
public class DeleteJobType {

    private final JobTypeOrgOrgRepository jobTypeOrgRepository;
    private final VacancyOrgRepository vacancyRepository;
    private final EmployeeOrgRepository employeeRepository;

    public String execute(String oid) {
        JobType jobType = jobTypeOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
        jobType.setStatus(StatusData.DELETED);
        jobType.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.isExist("jobType._id", oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete employment status because it's used in vacancy data.");
        }
        //</editor-fold>

        //<editor-fold desc="Check if used in Employee">
        boolean existInEmployee = employeeRepository.isExist("employment.jobType._id", oid);
        if (existInEmployee) {
            throw new NoSuchElementException("Can't delete employment status because it's used in employee data.");
        }
        //</editor-fold>

        jobTypeOrgRepository.save(jobType);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
