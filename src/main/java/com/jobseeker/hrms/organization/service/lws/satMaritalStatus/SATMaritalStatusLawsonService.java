package com.jobseeker.hrms.organization.service.lws.satMaritalStatus;

import com.jobseeker.hrms.organization.service.baseContract.BaseSATMaritalStatus;
import com.jobseeker.hrms.organization.service.lws.satMaritalStatus.query.LawsonGetSATMaritalStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class SATMaritalStatusLawsonService<T> extends BaseSATMaritalStatus<T> {
    @Autowired
    @Qualifier("lawsonGetSATMaritalStatuses")
    private LawsonGetSATMaritalStatuses lawsonGetSATMaritalStatuses;
    
    @Override
    public Page<T> getMaritalStatuses(Map<Object, Object> params) {
        return (Page<T>) lawsonGetSATMaritalStatuses.execute(params);
    }
}
