package com.jobseeker.hrms.organization.repositories.basic.organization.workplacement;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Workplacement;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface WorkplacementOrgOrgRepositoryExtend {

	Optional<Workplacement> findFirstByActive(String positionId);

	Page<Workplacement> findByDataTables(PaginationParam param);
}
