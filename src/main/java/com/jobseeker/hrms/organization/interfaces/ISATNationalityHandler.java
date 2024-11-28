package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ISATNationalityHandler<T> {
    Page<T> getSATNationalities(Map<Object, Object> params);
}
