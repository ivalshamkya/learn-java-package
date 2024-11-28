package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IApprovalHandler;
import com.jobseeker.hrms.organization.interfaces.IJobLevelHandler;
import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseJobLevel<T> implements IJobLevelHandler<T> {

	@Override
	public Page<T> getJobLevels(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getJobLevel(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T createJobLevel(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateJobLevel(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteJobLevel(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
