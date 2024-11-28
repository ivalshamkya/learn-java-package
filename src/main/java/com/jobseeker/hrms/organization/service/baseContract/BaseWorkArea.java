package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IWorkAreaHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BaseWorkArea<T> implements IWorkAreaHandler<T> {

	@Override
	public Page<T> getWorkAreas(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get WorkAreas not supported yet.");
	}

	@Override
	public T getWorkArea(String oid) {
		throw new UnsupportedOperationException("Get WorkArea not supported yet.");
	}

	@Override
	public String deleteWorkArea(String oid) {
		throw new UnsupportedOperationException("Delete WorkArea not supported yet.");
	}

	@Override
	public T updateWorkArea(Map<Object, Object> request, String oid) {
		throw new UnsupportedOperationException("Update WorkArea not supported yet.");
	}

	@Override
	public T createWorkArea(Map<Object, Object> request) {
		throw new UnsupportedOperationException("Create WorkArea not supported yet.");
	}
}
