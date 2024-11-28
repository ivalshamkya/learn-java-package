package com.jobseeker.hrms.organization.repositories.lws.organization.position;

import org.jobseeker.organization.lawson.PositionLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionOrgLWSRepository extends HelperOrganizationRepository<PositionLawson>, PositionOrgLWSRepositoryExtend {
}
