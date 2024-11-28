package com.jobseeker.hrms.organization.service.basic.softSkill;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseSoftSkill;
import com.jobseeker.hrms.organization.service.basic.softSkill.command.CreateSoftSkill;
import com.jobseeker.hrms.organization.service.basic.softSkill.command.DeleteSoftSkill;
import com.jobseeker.hrms.organization.service.basic.softSkill.command.UpdateSoftSkill;
import com.jobseeker.hrms.organization.service.basic.softSkill.query.GetSoftSkill;
import com.jobseeker.hrms.organization.service.basic.softSkill.query.GetSoftSkills;
import jakarta.validation.Valid;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class SoftSkillService<T> extends BaseSoftSkill<T> {
    
    //<editor-fold desc="createSoftSkill">
    @Autowired
    @Qualifier("createSoftSkill")
    private CreateSoftSkill createSoftSkill;
    
    @Override
    public T createSoftSkill(Map<Object, Object> request) throws Exception {
        SoftSkillDataRequest dataRequest = RequestHandler.remapToData(request, SoftSkillDataRequest.class);
        return (T) createSoftSkill.execute(dataRequest);
    }
    //</editor-fold>
    
    //<editor-fold desc="updateSoftSkill">
    @Autowired
    @Qualifier("updateSoftSkill")
    private UpdateSoftSkill updateSoftSkill;
    
    @Override
    public T updateSoftSkill(Map<Object, Object> request, String oid) throws Exception {
        SoftSkillDataRequest dataRequest = RequestHandler.remapToData(request, SoftSkillDataRequest.class);
        return (T) updateSoftSkill.execute(dataRequest, oid);
    }
    //</editor-fold>
    
    //<editor-fold desc="getSoftSkills">
    @Autowired
    @Qualifier("getSoftSkills")
    private GetSoftSkills getSoftSkills;
    
    @Override
    public Page<T> getSoftSkills(Map<Object, Object> param) {
        PaginationParam paramRequest = RequestHandler.remapToData(param, PaginationParam.class);
        return (Page<T>) getSoftSkills.execute(paramRequest);
    }
    //</editor-fold>
    
    //<editor-fold desc="getSoftSkill">
    @Autowired
    @Qualifier("getSoftSkill")
    private GetSoftSkill getSoftSkill;
    
    @Override
    public T getSoftSkill(String oid) {
        return (T) getSoftSkill.execute(oid);
    }
    //</editor-fold>
    
    //<editor-fold desc="deleteSoftSkill">
    @Autowired
    @Qualifier("deleteSoftSkill")
    private DeleteSoftSkill deleteSoftSkill;
    
    @Override
    public String deleteSoftSkill(String oid) {
        return deleteSoftSkill.execute(oid);
    }
    //</editor-fold>
}
