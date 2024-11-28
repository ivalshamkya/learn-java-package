package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import com.jobseeker.hrms.organization.interfaces.IJobLevelHandler;
import com.jobseeker.hrms.organization.interfaces.IJobTypeHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseJobType<T> implements IJobTypeHandler<T> {

	@Override
	public Page<T> getJobTypes(Map<Object, Object> paginationJobTypeParam) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getJobType(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T createJobType(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateJobType(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteJobType(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
