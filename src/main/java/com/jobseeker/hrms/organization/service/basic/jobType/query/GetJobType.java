package com.jobseeker.hrms.organization.service.basic.jobType.query;

import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IJobTypeMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobType.JobTypeOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.JobType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getJobType")
public class GetJobType {

    private final JobTypeOrgOrgRepository jobTypeOrgRepository;

    private final IJobTypeMapper jobTypeMapper;

    public JobTypeDataResponse execute(String oid) {
        JobType jobType = jobTypeOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
        return jobTypeMapper.toJobTypeDataResponse(jobType);
    }
}
