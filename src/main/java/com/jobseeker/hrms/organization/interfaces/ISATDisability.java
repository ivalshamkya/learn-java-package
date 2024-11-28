package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ISATDisability<T> {
    Page<T> getDisabilities(Map<Object, Object> params);
}
