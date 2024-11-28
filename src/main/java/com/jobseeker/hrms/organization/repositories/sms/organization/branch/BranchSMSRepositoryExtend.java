package com.jobseeker.hrms.organization.repositories.sms.organization.branch;

import com.jobseeker.hrms.organization.data.sms.branch.PaginationBranchSmsParams;
import org.jobseeker.organization.sms.BranchSMS;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BranchSMSRepositoryExtend {
	Optional<BranchSMS> findFirstByActive(String branchSMSId);
	Page<BranchSMS> findByDataTables(PaginationBranchSmsParams param);
}
