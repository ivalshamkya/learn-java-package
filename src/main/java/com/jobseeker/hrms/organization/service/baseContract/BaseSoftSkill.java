package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ISoftSkillHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract  class BaseSoftSkill<T> implements ISoftSkillHandler<T> {
    @Override
    public T createSoftSkill(Map<Object, Object> request) throws Exception {
        throw new UnsupportedOperationException("Create soft skill not supported yet.");
    }
    
    @Override
    public T updateSoftSkill(Map<Object, Object> request, String oid) throws Exception {
        throw new UnsupportedOperationException("Update soft skill not supported yet.");
    }
    
    @Override
    public Page<T> getSoftSkills(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Get soft skills not supported yet.");
    }
    
    @Override
    public T getSoftSkill(String oid) {
        throw new UnsupportedOperationException("Get soft skill not supported yet.");
    }
    
    @Override
    public String deleteSoftSkill(String oid) {
        throw new UnsupportedOperationException("Delete soft skill not supported yet.");
    }
}
