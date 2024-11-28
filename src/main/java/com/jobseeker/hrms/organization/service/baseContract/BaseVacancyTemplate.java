package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IVacancyTemplateHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseVacancyTemplate<T, U> implements IVacancyTemplateHandler<T, U> {

	@Override
	public Page<T> getJobTemplates(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public U getJobTemplate(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public U createJobTemplate(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public U updateJobTemplate(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteJobTemplate(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
