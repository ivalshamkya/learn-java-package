package com.jobseeker.hrms.organization.service.lws.satDisability;

import com.jobseeker.hrms.organization.service.baseContract.BaseSATDisability;
import com.jobseeker.hrms.organization.service.lws.satDisability.query.LawsonGetSATDisabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class SATDisabilityLawsonService<T> extends BaseSATDisability<T> {
    @Autowired
    @Qualifier("lawsonGetSATDisabilities")
    private LawsonGetSATDisabilities lawsonGetSATDisabilities;
    
    
    @Override
    public Page<T> getDisabilities(Map<Object, Object> params) {
        return (Page<T>) lawsonGetSATDisabilities.execute(params);
    }
}
