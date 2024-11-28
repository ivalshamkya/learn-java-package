package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IWorkAreaHandler<T> {
	Page<T> getWorkAreas(Map<Object, Object> param);

	T getWorkArea(String oid);

	T createWorkArea(Map<Object, Object> request) throws Exception;

	T updateWorkArea(Map<Object, Object> request, String oid) throws Exception;

	String deleteWorkArea(String oid) throws Exception;

}