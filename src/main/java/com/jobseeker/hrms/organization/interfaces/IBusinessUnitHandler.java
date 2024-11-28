package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IBusinessUnitHandler<T> {
	Page<T> getBusinessUnits(Map<Object, Object> param);

	T getBusinessUnit(String oid);

	T createBusinessUnit(Map<Object, Object> request) throws Exception;

	T updateBusinessUnit(Map<Object, Object> request, String oid) throws Exception;

	String deleteBusinessUnit(String oid) throws Exception;
}