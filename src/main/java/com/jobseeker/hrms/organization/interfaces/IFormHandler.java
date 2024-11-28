package com.jobseeker.hrms.organization.interfaces;

import java.util.List;

public interface IFormHandler<T> {
	List<T> getFormByCode(String code);
}
