package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IEmailTemplateHandler<T> {
	Page<T> getEmailTemplates(Map<Object, Object> param);
}