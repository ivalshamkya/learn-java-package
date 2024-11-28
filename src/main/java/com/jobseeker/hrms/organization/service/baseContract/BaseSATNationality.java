package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ISATNationalityHandler;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public abstract class BaseSATNationality<T> implements ISATNationalityHandler<T> {
    @Override
    public Page<T> getSATNationalities(Map<Object, Object> params) {
        throw new UnsupportedOperationException("Get SAT Nationalities not supported yet.");
    }
}
