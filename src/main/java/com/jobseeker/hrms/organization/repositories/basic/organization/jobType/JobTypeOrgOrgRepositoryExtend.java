package com.jobseeker.hrms.organization.repositories.basic.organization.jobType;

import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import org.jobseeker.organization.JobType;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface JobTypeOrgOrgRepositoryExtend {

	Optional<JobType> findFirstByActive(String jobTypeId);

	Page<JobType> findByDataTables(PaginationJobTypeParam param);
}
