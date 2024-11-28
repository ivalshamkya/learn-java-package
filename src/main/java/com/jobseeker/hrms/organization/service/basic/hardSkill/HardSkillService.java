package com.jobseeker.hrms.organization.service.basic.hardSkill;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseHardSkill;
import com.jobseeker.hrms.organization.service.basic.hardSkill.command.CreateHardSkill;
import com.jobseeker.hrms.organization.service.basic.hardSkill.command.DeleteHardSkill;
import com.jobseeker.hrms.organization.service.basic.hardSkill.command.UpdateHardSkill;
import com.jobseeker.hrms.organization.service.basic.hardSkill.query.GetHardSkill;
import com.jobseeker.hrms.organization.service.basic.hardSkill.query.GetHardSkills;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class HardSkillService<T> extends BaseHardSkill<T> {
    
    //<editor-fold desc="createHardSkill">
    @Autowired
    @Qualifier("createHardSkill")
    private CreateHardSkill createHardSkillService;
    
    @Override
    public T createHardSkill(Map<Object, Object> request) throws Exception {
        HardSkillDataRequest dataRequest = RequestHandler.remapToData(request, HardSkillDataRequest.class);
        return (T) createHardSkillService.execute(dataRequest);
    }
    //</editor-fold>
    
    //<editor-fold desc="updateHardSkill">
    @Autowired
    @Qualifier("updateHardSkill")
    private UpdateHardSkill updateHardSkillService;
    
    @Override
    public T updateHardSkill(Map<Object, Object> request, String oid) throws Exception {
        HardSkillDataRequest dataRequest = RequestHandler.remapToData(request, HardSkillDataRequest.class);
        return (T) updateHardSkillService.execute(dataRequest, oid);
    }
    //</editor-fold>
    
    //<editor-fold desc="getHardSkills">
    @Autowired
    @Qualifier("getHardSkills")
    private GetHardSkills getHardSkills;
    
    @Override
    public Page<T> getHardSkills(Map<Object, Object> request) {
        PaginationParam paramRequest = RequestHandler.remapToData(request, PaginationParam.class);
        return (Page<T>) getHardSkills.execute(paramRequest);
    }
    //</editor-fold>
    
    //<editor-fold desc="getHardSkill">
    @Autowired
    @Qualifier("getHardSkill")
    private GetHardSkill getHardSkill;
    
    @Override
    public T getHardSkill(String oid) {
        return (T) getHardSkill.execute(oid);
    }
    //</editor-fold>
    
    //<editor-fold desc="deleteHardSkill">
    @Autowired
    @Qualifier("deleteHardSkill")
    private DeleteHardSkill deleteHardSkill;
    
    @Override
    public String deleteHardSkill(String oid) {
        return deleteHardSkill.execute(oid);
    }
    //</editor-fold>
}
