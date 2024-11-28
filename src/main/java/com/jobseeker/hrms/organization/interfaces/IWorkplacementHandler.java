package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IWorkplacementHandler<T> {
	Page<T> getWorkplacements(Map<Object, Object> param);

	T getWorkplacement(String oid);

	T createWorkplacement(Map<Object, Object> request) throws Exception;

	T updateWorkplacement(Map<Object, Object> request, String oid) throws Exception;

	String deleteWorkplacement(String oid) throws Exception;
}