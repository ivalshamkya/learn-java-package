package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ICustomDataHandler;
import com.jobseeker.hrms.organization.interfaces.ICustomGroupDataHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BaseCustomGroupData<T> implements ICustomGroupDataHandler<T> {

	@Override
	public Page<T> getPaginatedCustomGroupData(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Custom Sources not supported yet.");
	}
}
