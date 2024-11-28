package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ISATDisability;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseSATDisability<T> implements ISATDisability<T> {
    @Override
    public Page<T> getDisabilities(Map<Object, Object> params) {
        throw new UnsupportedOperationException("Get SAT Disabilities not supported yet.");
    }
}
