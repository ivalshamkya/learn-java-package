package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IEmailTemplateHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BaseEmailTemplate<T> implements IEmailTemplateHandler<T> {

	@Override
	public Page<T> getEmailTemplates(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Get Work Area not supported yet.");
	}
}
