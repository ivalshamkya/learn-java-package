package com.jobseeker.hrms.organization.service.lws.satNationality;

import com.jobseeker.hrms.organization.service.baseContract.BaseSATNationality;
import com.jobseeker.hrms.organization.service.lws.satNationality.query.LawsonGetSATNationalities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class SATNationalityLawsonService<T> extends BaseSATNationality<T> {
    @Autowired
    @Qualifier("lawsonGetSATNationalities")
    private LawsonGetSATNationalities lawsonGetSATNationalities;
    
    @Override
    public Page<T> getSATNationalities(Map<Object, Object> params) {
        return (Page<T>) lawsonGetSATNationalities.execute(params);
    }
}
