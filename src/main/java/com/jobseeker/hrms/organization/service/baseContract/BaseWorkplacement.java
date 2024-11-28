package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IWorkplacementHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class BaseWorkplacement<T> implements IWorkplacementHandler<T> {

	@Override
	public Page<T> getWorkplacements(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Workplacements not supported yet.");
	}

	@Override
	public T getWorkplacement(String oid) {
		throw new UnsupportedOperationException("Get Workplacement not supported yet.");
	}

	@Override
	public String deleteWorkplacement(String oid) {
		throw new UnsupportedOperationException("Delete Workplacement not supported yet.");
	}

	@Override
	public T updateWorkplacement(Map<Object, Object> request, String oid) {
		throw new UnsupportedOperationException("Update Workplacement not supported yet.");
	}

	@Override
	public T createWorkplacement(Map<Object, Object> request) {
		throw new UnsupportedOperationException("Create Workplacement not supported yet.");
	}
}
