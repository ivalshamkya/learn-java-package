package com.jobseeker.hrms.organization.service.basic.jobType.query;

import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataResponse;
import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import com.jobseeker.hrms.organization.mapper.basic.IJobTypeMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobType.JobTypeOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getJobTypes")
public class GetJobTypes {

    private final JobTypeOrgOrgRepository jobTypeOrgRepository;

    private final IJobTypeMapper jobTypeMapper;

    public Page<JobTypeDataResponse> execute(PaginationJobTypeParam param) {
        return jobTypeOrgRepository.findByDataTables(param)
                .map(jobTypeMapper::toJobTypeDataResponse);
    }
}
