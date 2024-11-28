package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IBusinessUnitHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BaseBusinessUnit<T> implements IBusinessUnitHandler<T> {
	@Override
	public Page<T> getBusinessUnits(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Work Area not supported yet.");
	}

	@Override
	public T getBusinessUnit(String oid) {
		throw new UnsupportedOperationException("Get BusinessUnit not supported yet.");
	}

	@Override
	public String deleteBusinessUnit(String oid) {
		throw new UnsupportedOperationException("Delete BusinessUnit not supported yet.");
	}

	@Override
	public T updateBusinessUnit(Map<Object, Object> request, String oid) {
		throw new UnsupportedOperationException("Update BusinessUnit not supported yet.");
	}

	@Override
	public T createBusinessUnit(Map<Object, Object> request) {
		throw new UnsupportedOperationException("Create BusinessUnit not supported yet.");
	}
}
