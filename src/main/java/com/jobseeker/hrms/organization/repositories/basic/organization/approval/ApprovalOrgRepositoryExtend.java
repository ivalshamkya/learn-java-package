package com.jobseeker.hrms.organization.repositories.basic.organization.approval;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import org.jobseeker.organization.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ApprovalOrgRepositoryExtend {

	Optional<Approval> findFirstByActive(String approvalId);

	Page<Approval> findByDataTables(ApprovalDatatableDataRequest param);

	Page<Approval> findAllByActive(Pageable pageable);

	List<Approval> findAllByActive();

	void deleteByEmployeeId(List<String> employeeId);

}
