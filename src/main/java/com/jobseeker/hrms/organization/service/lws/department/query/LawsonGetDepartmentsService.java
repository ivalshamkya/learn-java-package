package com.jobseeker.hrms.organization.service.lws.department.query;

import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataResponse;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.mapper.lws.IDepartmentLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.department.DepartmentLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetDepartmentsService")
public class LawsonGetDepartmentsService {

	private final DepartmentLWSRepository departmentLWSRepository;
	private final IDepartmentLWSMapper departmentMapper;

	public Page<DepartmentLWSDataResponse> execute(PaginationLWSParams params) {
		return departmentLWSRepository.findByDataTables(params)
				.map(departmentMapper::toDepartmentDataResponse);
	}
}
