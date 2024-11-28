package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ICustomDataHandler<T> {

	Page<T> getPaginatedCustomData(Map<Object, Object> params);
}