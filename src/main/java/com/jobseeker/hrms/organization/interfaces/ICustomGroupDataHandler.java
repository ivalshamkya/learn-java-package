package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ICustomGroupDataHandler<T> {

	Page<T> getPaginatedCustomGroupData(Map<Object, Object> params);
}