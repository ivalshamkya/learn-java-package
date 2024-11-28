package com.jobseeker.hrms.organization.repositories.basic.organization.branch;

import org.jobseeker.organization.Branch;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;

public interface BranchOrgRepositoryExtend {

	Optional<Branch> findFirstByActive(String branchSMSId);

	Page<Branch> findByDataTables(Map<Object, Object> param);
}
