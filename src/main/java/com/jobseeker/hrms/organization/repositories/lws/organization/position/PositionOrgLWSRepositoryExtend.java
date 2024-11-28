package com.jobseeker.hrms.organization.repositories.lws.organization.position;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.lawson.PositionLawson;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PositionOrgLWSRepositoryExtend {

	Optional<PositionLawson> findFirstByActive(String positionId);

	Page<PositionLawson> findByDataTables(PaginationParam param);
}
