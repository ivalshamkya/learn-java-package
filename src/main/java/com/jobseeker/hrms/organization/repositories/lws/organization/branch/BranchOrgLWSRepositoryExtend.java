package com.jobseeker.hrms.organization.repositories.lws.organization.branch;

import org.jobseeker.organization.lawson.BranchLawson;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;

public interface BranchOrgLWSRepositoryExtend {

	Optional<BranchLawson> findFirstByActive(String branchSMSId);

	Page<BranchLawson> findByDataTables(Map<Object, Object> param);
}
