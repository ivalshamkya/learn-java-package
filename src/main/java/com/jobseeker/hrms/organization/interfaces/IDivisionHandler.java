package com.jobseeker.hrms.organization.interfaces;

import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IDivisionHandler<T> {

	Page<T> getDivisions(Map<Object, Object> param);

	T getDivision(String oid);

	T createDivision(Map<Object, Object> request) throws Exception;

	T updateDivision(Map<Object, Object> request, String oid) throws Exception;

	String deleteDivision(String oid) throws Exception;

}
