package com.jobseeker.hrms.organization.interfaces;

public interface IFilterHandler<T> {

	T getDepartmentHasActiveVacancy();

	T getCityHasActiveVacancy();

	T getWorkAreaHasActiveVacancy();

}
