package com.jobseeker.hrms.organization.service.basic.jobLevel.query;

import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IJobLevelMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel.JobLevelOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.JobLevel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getJobLevel")
public class GetJobLevel {

    private final JobLevelOrgOrgRepository jobLevelOrgRepository;

    private final IJobLevelMapper jobLevelMapper;

    public JobLevelDataResponse execute(String oid) {
        JobLevel jobLevel = jobLevelOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
        return jobLevelMapper.toJobLevelDataResponse(jobLevel);
    }
}
