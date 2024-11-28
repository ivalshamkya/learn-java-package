package com.jobseeker.hrms.organization.interfaces;

import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IVacancyTemplateHandler<T, U> {
	Page<T> getJobTemplates(Map<Object, Object> param);

	U getJobTemplate(String oid);

	U createJobTemplate(Map<Object, Object> request) throws Exception;

	U updateJobTemplate(Map<Object, Object> request, String oid) throws Exception;

	String deleteJobTemplate(String oid) throws Exception;
}
