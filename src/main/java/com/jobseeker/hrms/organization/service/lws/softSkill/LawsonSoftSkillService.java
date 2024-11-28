package com.jobseeker.hrms.organization.service.lws.softSkill;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseSoftSkill;
import com.jobseeker.hrms.organization.service.lws.softSkill.command.LawsonCreateSoftSkill;
import com.jobseeker.hrms.organization.service.lws.softSkill.command.LawsonDeleteSoftSkill;
import com.jobseeker.hrms.organization.service.lws.softSkill.command.LawsonUpdateSoftSkill;
import com.jobseeker.hrms.organization.service.lws.softSkill.query.LawsonGetSoftSkill;
import com.jobseeker.hrms.organization.service.lws.softSkill.query.LawsonGetSoftSkills;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class LawsonSoftSkillService<T> extends BaseSoftSkill<T> {
    @Autowired
    @Qualifier("lawsonCreateSoftSkill")
    private LawsonCreateSoftSkill lawsonCreateSoftSkill;
    
    @Override
    public T createSoftSkill(Map<Object, Object> request) throws Exception {
        SoftSkillLawsonDataRequest dataRequest = RequestHandler.remapToData(request, SoftSkillLawsonDataRequest.class);
        return (T) lawsonCreateSoftSkill.execute(dataRequest);
    }
    
    @Autowired
    @Qualifier("lawsonUpdateSoftSkill")
    LawsonUpdateSoftSkill lawsonUpdateSoftSkill;
    
    @Override
    public T updateSoftSkill(Map<Object, Object> request, String oid) throws Exception {
        SoftSkillLawsonDataRequest dataRequest = RequestHandler.remapToData(request, SoftSkillLawsonDataRequest.class);
        return (T) lawsonUpdateSoftSkill.execute(dataRequest, oid);
    }
    
    @Autowired
    @Qualifier("lawsonGetSoftSkills")
    LawsonGetSoftSkills lawsonGetSoftSkills;
    
    @Override
    public Page<T> getSoftSkills(Map<Object, Object> request) {
        PaginationParam paramRequest = RequestHandler.remapToData(request, PaginationParam.class);
        return (Page<T>) lawsonGetSoftSkills.execute(paramRequest);
    }
    
    @Autowired
    @Qualifier("lawsonGetSoftSkill")
    LawsonGetSoftSkill lawsonGetSoftSkill;
    
    @Override
    public T getSoftSkill(String oid) {
        return (T) lawsonGetSoftSkill.execute(oid);
    }
    
    @Autowired
    @Qualifier("lawsonDeleteSoftSkill")
    LawsonDeleteSoftSkill lawsonDeleteSoftSkill;
    
    @Override
    public String deleteSoftSkill(String oid) {
        return lawsonDeleteSoftSkill.execute(oid);
    }
}
