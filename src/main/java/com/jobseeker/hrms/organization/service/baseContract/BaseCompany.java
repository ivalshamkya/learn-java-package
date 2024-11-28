package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IBranchHandler;
import com.jobseeker.hrms.organization.interfaces.ICompanyHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseCompany<T> implements ICompanyHandler<T> {

	@Override
	public T getCompany() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T initCompany(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateCompany(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
