package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IBranchHandler;
import com.jobseeker.hrms.organization.interfaces.IOfferingLetterHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseOfferingLetter<T> implements IOfferingLetterHandler<T> {

	@Override
	public T getOfferingLetter() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateOfferingLetter(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
