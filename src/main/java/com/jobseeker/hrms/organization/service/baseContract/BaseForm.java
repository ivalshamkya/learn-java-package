package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IApprovalHandler;
import com.jobseeker.hrms.organization.interfaces.IFormHandler;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public abstract class BaseForm<T> implements IFormHandler<T> {

	@Override
	public List<T> getFormByCode(String code) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
