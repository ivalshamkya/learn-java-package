package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IReligionOrganizationHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseReligionOrganization<T> implements IReligionOrganizationHandler<T> {
    @Override
    public Page<T> getReligionsOrganizations(Map<Object, Object> param) {
        throw new UnsupportedOperationException("Get religions organization not yet supported.");
    }
}
