package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IDepartmentHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseDepartment<T> implements IDepartmentHandler<T> {

	@Override
	public Page<T> getDepartments(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Departments not supported yet.");
	}

	@Override
	public T getDepartment(String oid) {
		throw new UnsupportedOperationException("Get Department not supported yet.");
	}

	@Override
	public String deleteDepartment(String oid) {
		throw new UnsupportedOperationException("Delete Department not supported yet.");
	}

	@Override
	public T updateDepartment(Map<Object, Object> request, String oid) {
		throw new UnsupportedOperationException("Update Department not supported yet.");
	}

	@Override
	public T createDepartment(Map<Object, Object> request) {
		throw new UnsupportedOperationException("Create Department not supported yet.");
	}
}
