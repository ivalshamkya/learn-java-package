package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ISATMaritalStatusHandler<T> {
    Page<T> getMaritalStatuses(Map<Object, Object> params);
}