package com.jobseeker.hrms.organization.interfaces;

import java.util.Map;

public interface ICompanyHandler<T> {

	T getCompany();

	T initCompany(Map<Object, Object> request) throws Exception;

	T updateCompany(Map<Object, Object> request) throws Exception;
}
