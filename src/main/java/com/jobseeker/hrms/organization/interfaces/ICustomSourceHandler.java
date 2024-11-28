package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ICustomSourceHandler<T> {
	Page<T> getCustomSources(Map<Object, Object> param);
}