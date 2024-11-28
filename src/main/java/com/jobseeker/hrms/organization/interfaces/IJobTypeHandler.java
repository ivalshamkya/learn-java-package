package com.jobseeker.hrms.organization.interfaces;

import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IJobTypeHandler<T> {
	Page<T> getJobTypes(Map<Object, Object> paginationJobTypeParam);

	T getJobType(String oid);

	T createJobType(Map<Object, Object> request) throws Exception;

	T updateJobType(Map<Object, Object> request, String oid) throws Exception;

	String deleteJobType(String oid) throws Exception;
}