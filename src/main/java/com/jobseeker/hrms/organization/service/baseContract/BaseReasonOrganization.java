package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IReasonOrganizationHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseReasonOrganization<T> implements IReasonOrganizationHandler<T> {
    @Override
    public Page<T> getDataReasons(Map<Object, Object> params) {
        throw new UnsupportedOperationException("Get Data Reasons not supported yet.");
    }
}
