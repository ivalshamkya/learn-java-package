package com.jobseeker.hrms.organization.repositories.lws.organization.workArea;

import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkAreaOrgLWSRepository extends HelperOrganizationRepository<WorkAreaLawson>, WorkAreaOrgLWSRepositoryExtend {
}
