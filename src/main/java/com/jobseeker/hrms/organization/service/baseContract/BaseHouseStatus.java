package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IHouseStatusHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseHouseStatus<T> implements IHouseStatusHandler<T> {
    @Override
    public Page<T> getHouseStatuses(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Get house statuses not supported yet.");
    }
}
