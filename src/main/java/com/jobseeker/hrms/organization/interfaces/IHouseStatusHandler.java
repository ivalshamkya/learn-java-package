package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IHouseStatusHandler<T> {
    Page<T> getHouseStatuses(Map<Object, Object> request);
}
