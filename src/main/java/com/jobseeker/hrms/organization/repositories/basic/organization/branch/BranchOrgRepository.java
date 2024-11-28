package com.jobseeker.hrms.organization.repositories.basic.organization.branch;

import org.jobseeker.organization.Branch;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOrgRepository extends HelperOrganizationRepository<Branch>, BranchOrgRepositoryExtend {

}
