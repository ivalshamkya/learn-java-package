package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IImportTemplateHandler<T> {
	T getImportTemplate(String type);
}