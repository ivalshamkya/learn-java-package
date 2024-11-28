package com.jobseeker.hrms.organization.service.lws.department.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IDepartmentLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.department.DepartmentLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.lawson.DepartmentLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetDepartmentService")
public class LawsonGetDepartmentService {

	private final DepartmentLWSRepository departmentLWSRepository;
	private final IDepartmentLWSMapper departmentMapper;

	public DepartmentLWSDataResponse execute(String oid) {
		DepartmentLawson data = departmentLWSRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));

		return departmentMapper.toDepartmentDataResponse(data);
	}
}
