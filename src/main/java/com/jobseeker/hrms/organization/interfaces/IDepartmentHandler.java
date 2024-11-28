package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IDepartmentHandler<T> {
	Page<T> getDepartments(Map<Object, Object> param);

	T getDepartment(String oid);

	T createDepartment(Map<Object, Object> request) throws Exception;

	T updateDepartment(Map<Object, Object> request, String oid) throws Exception;

	String deleteDepartment(String oid) throws Exception;
}