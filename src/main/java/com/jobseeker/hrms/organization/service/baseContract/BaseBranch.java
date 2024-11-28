package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IBranchHandler;
import com.jobseeker.hrms.organization.interfaces.IPositionHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseBranch<T> implements IBranchHandler<T> {

	@Override
	public Page<T> getBranches(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getBranch(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T createBranch(Map<Object, Object> request) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateBranch(Map<Object, Object> request, String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteBranch(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
