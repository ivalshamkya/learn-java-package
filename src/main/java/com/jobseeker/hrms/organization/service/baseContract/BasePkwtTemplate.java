package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IPkwtTemplateHandler;

import java.util.List;

//@SuppressWarnings("unchecked")
public abstract class BasePkwtTemplate<T> implements IPkwtTemplateHandler<T> {
	@Override
	public List<T> getPkwtTemplates() {
		throw new UnsupportedOperationException("Get PKWT Templates not supported yet.");
	}

	@Override
	public T getPkwtTemplate(String oid) {
		throw new UnsupportedOperationException("Get PKWT Template not supported yet.");
	}
}
