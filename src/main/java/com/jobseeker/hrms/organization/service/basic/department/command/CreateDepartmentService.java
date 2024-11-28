package com.jobseeker.hrms.organization.service.basic.department.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataRequest;
import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IDepartmentMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.department.DepartmentOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createDepartmentService")
public class CreateDepartmentService {

	private final CompanyOrgRepository companyRepository;
	private final DepartmentOrgRepository departmentRepository;

	private final ICompanyMapper companyMapper;
	private final IDepartmentMapper departmentMapper;


	public DepartmentDataResponse execute(DepartmentDataRequest request) {
		Department department = composeBranch(request, null);

		return departmentMapper.toDepartmentDataResponse(department);
	}

	private Department composeBranch(DepartmentDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

		Department department = new Department();
		if (oid != null) {
			department = departmentRepository.findFirstByActive(oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));
			department.setUpdatedAt(LocalDateTime.now());
		} else {
			department.setStatus(StatusData.ACTIVE);
			department.setCreatedAt(LocalDateTime.now());
		}

		department.setCompany(companyMapper.toAttachCompany(company));

		departmentMapper.toWriteDepartment(department, request);
		return departmentRepository.save(department);
	}
}
