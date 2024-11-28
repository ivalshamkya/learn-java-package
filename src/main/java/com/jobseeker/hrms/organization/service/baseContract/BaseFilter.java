package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IFilterHandler;

@SuppressWarnings("unchecked")
public abstract class BaseFilter<T> implements IFilterHandler<T> {

	@Override
	public T getDepartmentHasActiveVacancy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getCityHasActiveVacancy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getWorkAreaHasActiveVacancy() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
