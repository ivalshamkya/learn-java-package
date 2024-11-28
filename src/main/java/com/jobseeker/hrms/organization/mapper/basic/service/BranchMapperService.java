package com.jobseeker.hrms.organization.mapper.basic.service;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataRequest;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.approval.ApprovalOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jobseeker.employee.repositories.EmployeeRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BranchMapperService {

	private final EmployeeOrgRepository employeeRepository;
	private final VacancyOrgRepository vacancyRepository;

	@Named("setTotalEmployee")
	public Long setTotalEmployee(String branchId) {
		if (branchId == null || branchId.isEmpty()) return null;
		return employeeRepository.countByBranch(branchId).orElse(0L);
	}

	@Named("setTotalVacancy")
	public Long setTotalVacancy(String branchId) {
		if (branchId == null || branchId.isEmpty()) return null;
		return vacancyRepository.countByBranchId(new ObjectId(branchId)).orElse(0L);
	}
}
