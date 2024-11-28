package com.jobseeker.hrms.organization.service.basic.religionOrganization;

import com.jobseeker.hrms.organization.service.baseContract.BaseReligionOrganization;
import com.jobseeker.hrms.organization.service.basic.religionOrganization.query.GetReligionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class ReligionOrganizationService<T> extends BaseReligionOrganization<T> {
    @Autowired
    @Qualifier("getReligionsService")
    private GetReligionsService getReligionsService;
    
    @Override
    public Page<T> getReligionsOrganizations(Map<Object, Object> param) {
        return (Page<T>) getReligionsService.execute(param);
    }
}
