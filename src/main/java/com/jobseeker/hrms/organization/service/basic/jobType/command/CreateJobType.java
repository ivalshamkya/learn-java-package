package com.jobseeker.hrms.organization.service.basic.jobType.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataRequest;
import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IJobTypeMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobType.JobTypeOrgOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.JobType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createJobType")
public class CreateJobType {

    private final CompanyOrgRepository companyRepository;
    private final JobTypeOrgOrgRepository jobTypeOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IJobTypeMapper jobTypeMapper;

    private final Validator validator;

    public JobTypeDataResponse execute(JobTypeDataRequest request) {
        JobType jobType = composeBranch(request, null);

        return jobTypeMapper.toJobTypeDataResponse(jobType);
    }

    private JobType composeBranch(JobTypeDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        JobType jobType = new JobType();
        if (oid != null) {
            jobType = jobTypeOrgRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
            jobType.setUpdatedAt(LocalDateTime.now());
        } else {
            jobType.setStatus(StatusData.ACTIVE);
            jobType.setCreatedAt(LocalDateTime.now());
        }

        jobType.setCompany(companyMapper.toAttachCompany(company));

        jobTypeMapper.toWriteJobType(jobType, request);
        return jobTypeOrgRepository.save(jobType);
    }

    private JobTypeDataRequest getJobTypeRequest(Map<Object, Object> request) {
        ObjectMapper mapper = new ObjectMapper();
        JobTypeDataRequest dataRequest = mapper.convertValue(request, JobTypeDataRequest.class);
        var hasAnyError_dataRequest = validator.validate(dataRequest);
        if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

        return dataRequest;
    }
}
