package com.jobseeker.hrms.organization.service.basic.department.query;

import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IDepartmentMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.department.DepartmentOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getDepartmentsService")
public class GetDepartmentsService {

	private final DepartmentOrgRepository departmentRepository;
	private final IDepartmentMapper departmentMapper;

	public Page<DepartmentDataResponse> execute(PaginationParam param) {
		return departmentRepository.findByDataTables(param)
				.map(departmentMapper::toDepartmentDataResponse);
	}
}
