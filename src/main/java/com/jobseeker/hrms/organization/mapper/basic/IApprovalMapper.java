package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataRequest;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.ApprovalMapperService;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.employee.Employee;
import org.jobseeker.organization.Approval;
import org.jobseeker.organization.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ApprovalMapperService.class})
public interface IApprovalMapper {

	@Mapping(source = "position.name", target = "position")
	@Mapping(source = "department.name", target = "department")
	@Mapping(source = "branch.name", target = "branch")
	ApprovalDataResponse toApprovalDataResponse(Approval approval);

	@Mapping(source = "employee.name", target = "employeeName")
	@Mapping(source = "position.name", target = "position")
	@Mapping(source = "department.name", target = "department")
	@Mapping(source = "branch.name", target = "branch")
	List<ApprovalDataResponse> toResponse(List<Approval> approval);

	EmployeeDataEmbed toAttachEmployee(Employee employee);

	Approval toSave(ApprovalDataRequest request);

	@Mapping(source = "logo.link", target = "logo")
	CompanyDataEmbed toAttachCompany(Company company);

}