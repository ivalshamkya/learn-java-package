package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IEmailTemplateHandler;
import com.jobseeker.hrms.organization.interfaces.IImportTemplateHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

//@SuppressWarnings("unchecked")
public abstract class BaseImportTemplate<T> implements IImportTemplateHandler<T> {

	@Override
	public T getImportTemplate(String type) {
		throw new UnsupportedOperationException("Get Import not supported yet.");
	}
}
