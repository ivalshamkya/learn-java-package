package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IHardSkillHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseHardSkill<T> implements IHardSkillHandler<T> {
    
    
    @Override
    public T createHardSkill(Map<Object, Object> request) throws Exception {
        throw new UnsupportedOperationException("Create hard skill not supported yet.");
    }
    
    @Override
    public T updateHardSkill(Map<Object, Object> request, String oid) throws Exception {
        throw new UnsupportedOperationException("Update hard skill not supported yet.");
    }
    
    @Override
    public Page<T> getHardSkills(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Get hard skills not supported yet.");
    }
    
    @Override
    public T getHardSkill(String oid) {
        throw new UnsupportedOperationException("Get hard skill not supported yet.");
    }
    
    @Override
    public String deleteHardSkill(String oid) {
        throw new UnsupportedOperationException("Delete hard skill not supported yet.");
    }
}
