package com.jobseeker.hrms.organization.service.basic.jobLevel.query;

import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IJobLevelMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel.JobLevelOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getJobLevels")
public class GetJobLevels {

    private final JobLevelOrgOrgRepository jobLevelOrgRepository;

    private final IJobLevelMapper jobLevelMapper;

    public Page<JobLevelDataResponse> execute(PaginationParam param) {
        return jobLevelOrgRepository.findByDataTables(param)
                .map(jobLevelMapper::toJobLevelDataResponse);
    }
}
