package com.jobseeker.hrms.organization.service.basic.reasonOrganization;

import com.jobseeker.hrms.organization.service.baseContract.BaseReasonOrganization;
import com.jobseeker.hrms.organization.service.basic.reasonOrganization.query.GetReasonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class ReasonOrganizationService<T> extends BaseReasonOrganization<T> {
    @Autowired
    @Qualifier("getReasonsService")
    private GetReasonsService getReasonsService;
    
    @Override
    public Page<T> getDataReasons(Map<Object, Object> params) {
        return (Page<T>) getReasonsService.execute(params);
    }
}
