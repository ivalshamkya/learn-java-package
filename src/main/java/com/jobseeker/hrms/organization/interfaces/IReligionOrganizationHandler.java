package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IReligionOrganizationHandler<T> {
    
    Page<T> getReligionsOrganizations(Map<Object,Object> param);
}
