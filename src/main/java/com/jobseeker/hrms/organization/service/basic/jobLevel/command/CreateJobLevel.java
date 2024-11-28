package com.jobseeker.hrms.organization.service.basic.jobLevel.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataRequest;
import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IJobLevelMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel.JobLevelOrgOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.JobLevel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createJobLevel")
public class CreateJobLevel {

    private final CompanyOrgRepository companyRepository;
    private final JobLevelOrgOrgRepository jobLevelOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IJobLevelMapper jobLevelMapper;

    private final Validator validator;

    public JobLevelDataResponse execute(JobLevelDataRequest request) {
        JobLevel jobLevel = composeBranch(request, null);

        return jobLevelMapper.toJobLevelDataResponse(jobLevel);
    }

    private JobLevel composeBranch(JobLevelDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        JobLevel jobLevel = new JobLevel();
        if (oid != null) {
            jobLevel = jobLevelOrgRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
            jobLevel.setUpdatedAt(LocalDateTime.now());
        } else {
            jobLevel.setStatus(StatusData.ACTIVE);
            jobLevel.setCreatedAt(LocalDateTime.now());
        }

        jobLevel.setCompany(companyMapper.toAttachCompany(company));

        jobLevelMapper.toWriteJobLevel(jobLevel, request);
        return jobLevelOrgRepository.save(jobLevel);
    }

    private JobLevelDataRequest getJobLevelRequest(Map<Object, Object> request) {
        ObjectMapper mapper = new ObjectMapper();
        JobLevelDataRequest dataRequest = mapper.convertValue(request, JobLevelDataRequest.class);
        var hasAnyError_dataRequest = validator.validate(dataRequest);
        if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

        return dataRequest;
    }
}
