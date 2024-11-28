package com.jobseeker.hrms.organization.repositories.lws.organization.division;

import org.jobseeker.organization.lawson.DivisionLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionLWSRepository extends HelperOrganizationRepository<DivisionLawson>, DivisionLWSRepositoryExtend {
}
