package com.jobseeker.hrms.organization.repositories.lws.organization.workplacement;

import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplacementOrgLWSRepository extends HelperOrganizationRepository<WorkplacementLawson>, WorkplacementOrgLWSRepositoryExtend {
}
