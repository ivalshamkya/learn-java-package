package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IReasonOrganizationHandler<T> {
    Page<T> getDataReasons(Map<Object, Object> params);
}
