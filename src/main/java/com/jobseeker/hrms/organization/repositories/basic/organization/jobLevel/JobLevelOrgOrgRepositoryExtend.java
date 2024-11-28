package com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.JobLevel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface JobLevelOrgOrgRepositoryExtend {

	Optional<JobLevel> findFirstByActive(String jobLevelId);

	Page<JobLevel> findByDataTables(PaginationParam param);
}
