package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IBranchHandler;
import com.jobseeker.hrms.organization.interfaces.IDivisionHandler;
import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseDivision<T> implements IDivisionHandler<T> {

	@Override
	public Page<T> getDivisions(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getDivision(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T createDivision(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateDivision(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteDivision(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
