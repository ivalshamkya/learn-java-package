package com.jobseeker.hrms.organization.repositories.lws.organization.businessUnit;

import org.jobseeker.organization.lawson.BusinessUnitLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUnitOrgLWSRepository extends HelperOrganizationRepository<BusinessUnitLawson>, BusinessUnitOrgLWSRepositoryExtend {
}
