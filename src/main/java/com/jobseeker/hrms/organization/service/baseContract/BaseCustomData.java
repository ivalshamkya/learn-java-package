package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ICustomDataHandler;
import com.jobseeker.hrms.organization.interfaces.ICustomSourceHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BaseCustomData<T> implements ICustomDataHandler<T> {

	@Override
	public Page<T> getPaginatedCustomData(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Custom Sources not supported yet.");
	}
}
