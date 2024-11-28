package com.jobseeker.hrms.organization.mapper.basic.service;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataRequest;
import com.jobseeker.hrms.organization.repositories.basic.organization.approval.ApprovalOrgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.jobseeker.employee.repositories.employee.EmployeeRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApprovalMapperService {

//	private final EmployeeRepository employeeRepository;
	private final ApprovalOrgRepository approvalOrgRepository;

	@Named("setApprovals")
	public void setApprovals(ApprovalDataRequest request) {
		log.info("request >>> ", request);
//		return null;
	}
}
