package com.jobseeker.hrms.organization.service.lws.hardSkill;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataRequest;
import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseHardSkill;
import com.jobseeker.hrms.organization.service.lws.hardSkill.command.LawsonCreateHardSkill;
import com.jobseeker.hrms.organization.service.lws.hardSkill.command.LawsonDeleteHardSkill;
import com.jobseeker.hrms.organization.service.lws.hardSkill.command.LawsonUpdateHardSkill;
import com.jobseeker.hrms.organization.service.lws.hardSkill.query.LawsonGetHardSkill;
import com.jobseeker.hrms.organization.service.lws.hardSkill.query.LawsonGetHardSkills;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class LawsonHardSkillService<T> extends BaseHardSkill<T> {
    
    @Autowired
    @Qualifier("lawsonCreateHardSkill")
    private LawsonCreateHardSkill lawsonCreateHardSkill;
    
    @Override
    public T createHardSkill(Map<Object, Object> request) throws Exception {
        HardSkillLawsonDataRequest dataRequest = RequestHandler.remapToData(request, HardSkillLawsonDataRequest.class);
        return (T) lawsonCreateHardSkill.execute(dataRequest);
    }
    
    @Autowired
    @Qualifier("lawsonUpdateHardSkill")
    private LawsonUpdateHardSkill lawsonUpdateHardSkill;
    
    @Override
    public T updateHardSkill(Map<Object, Object> request, String oid) throws Exception {
        HardSkillLawsonDataRequest dataRequest = RequestHandler.remapToData(request, HardSkillLawsonDataRequest.class);
        return (T) lawsonUpdateHardSkill.execute(dataRequest, oid);
    }
    
    @Autowired
    @Qualifier("lawsonGetHardSkills")
    private LawsonGetHardSkills lawsonGetHardSkills;
    
    @Override
    public Page<T> getHardSkills(Map<Object, Object> request) {
        PaginationParam paramRequest = RequestHandler.remapToData(request, PaginationParam.class);
        return (Page<T>) lawsonGetHardSkills.execute(paramRequest);
    }
    
    @Autowired
    @Qualifier("lawsonGetHardSkill")
    private LawsonGetHardSkill lawsonGetHardSkill;
    
    @Override
    public T getHardSkill(String oid) {
        return (T) lawsonGetHardSkill.execute(oid);
    }
    
    @Autowired
    @Qualifier("lawsonDeleteHardSkill")
    private LawsonDeleteHardSkill lawsonDeleteHardSkill;
    
    @Override
    public String deleteHardSkill(String oid) {
        return lawsonDeleteHardSkill.execute(oid);
    }
}
