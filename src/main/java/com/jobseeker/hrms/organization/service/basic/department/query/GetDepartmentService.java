package com.jobseeker.hrms.organization.service.basic.department.query;

import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IDepartmentMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.department.DepartmentOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getDepartmentService")
public class GetDepartmentService {

	private final DepartmentOrgRepository departmentRepository;
	private final IDepartmentMapper departmentMapper;

	public DepartmentDataResponse execute(String oid) {
		Department department = departmentRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));
		return departmentMapper.toDepartmentDataResponse(department);
	}
}
