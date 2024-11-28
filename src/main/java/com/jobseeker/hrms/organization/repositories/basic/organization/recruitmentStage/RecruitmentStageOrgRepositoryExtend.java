package com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.RecruitmentStage;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RecruitmentStageOrgRepositoryExtend {

	Optional<RecruitmentStage> findFirstByActive(String positionId);

	Page<RecruitmentStage> findByDataTables(PaginationParam param);
}
