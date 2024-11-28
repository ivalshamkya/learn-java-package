package com.jobseeker.hrms.organization.repositories.basic.organization.position;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Position;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PositionOrgRepositoryExtend {

	Optional<Position> findFirstByActive(String positionId);

	Page<Position> findByDataTables(PaginationParam param);
}
