package com.jobseeker.hrms.organization.interfaces;

import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IJobLevelHandler<T> {
	Page<T> getJobLevels(Map<Object, Object> param);

	T getJobLevel(String oid);

	T createJobLevel(Map<Object, Object> request) throws Exception;

	T updateJobLevel(Map<Object, Object> request, String oid) throws Exception;

	String deleteJobLevel(String oid) throws Exception;
}