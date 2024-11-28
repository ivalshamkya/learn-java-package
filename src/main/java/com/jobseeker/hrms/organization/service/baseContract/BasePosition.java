package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IPositionHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BasePosition<T> implements IPositionHandler<T> {
	@Override
	public Page<T> getPositions(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Position not supported yet.");
	}

	@Override
	public T getPosition(String oid) {
		throw new UnsupportedOperationException("Get Position not supported yet.");
	}

	@Override
	public String deletePosition(String oid) {
		throw new UnsupportedOperationException("Delete Position not supported yet.");
	}

	@Override
	public T updatePosition(Map<Object, Object> request, String oid) {
		throw new UnsupportedOperationException("Update Position not supported yet.");
	}

	@Override
	public T createPosition(Map<Object, Object> request) {
		throw new UnsupportedOperationException("Create Position not supported yet.");
	}
}
