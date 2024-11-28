package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ISATMaritalStatusHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseSATMaritalStatus<T> implements ISATMaritalStatusHandler<T> {
    @Override
    public Page<T> getMaritalStatuses(Map<Object, Object> params) {
        throw new UnsupportedOperationException("Get SAT Marital Statuses not supported yet.");
    }
}
