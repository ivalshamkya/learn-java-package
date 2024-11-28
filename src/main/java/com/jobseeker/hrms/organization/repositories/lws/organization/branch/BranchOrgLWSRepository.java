package com.jobseeker.hrms.organization.repositories.lws.organization.branch;

import org.jobseeker.organization.lawson.BranchLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOrgLWSRepository extends HelperOrganizationRepository<BranchLawson>, BranchOrgLWSRepositoryExtend {

}
