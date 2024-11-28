package com.jobseeker.hrms.organization.service.lws.workplacement;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseWorkplacement;
import com.jobseeker.hrms.organization.service.lws.workplacement.command.LawsonCreateWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.command.LawsonDeleteWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.command.LawsonUpdateWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.query.LawsonGetWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.query.LawsonGetWorkplacementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class WorkplacementLawsonService<T> extends BaseWorkplacement<T> {

    //<editor-fold desc="lawsonGetWorkplacements">
    @Autowired
    @Qualifier("lawsonGetWorkplacementsService")
    private LawsonGetWorkplacementsService lawsonGetWorkplacementsService;

    @Override
    public Page<T> getWorkplacements(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) lawsonGetWorkplacementsService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonGetWorkplacement">
    @Autowired
    @Qualifier("lawsonGetWorkplacementService")
    private LawsonGetWorkplacementService getWorkplacementervice;

    @Override
    public T getWorkplacement(String oid) {
        return (T) getWorkplacementervice.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonDeleteWorkplacement">
    @Autowired
    @Qualifier("lawsonDeleteWorkplacementService")
    private LawsonDeleteWorkplacementService deleteWorkplacementervice;

    @Override
    public String deleteWorkplacement(String oid) {
        return deleteWorkplacementervice.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonUpdateWorkplacement">
    @Autowired
    @Qualifier("lawsonUpdateWorkplacementService")
    private LawsonUpdateWorkplacementService updateWorkplacementervice;

    @Override
    public T updateWorkplacement(Map<Object, Object> request, String oid) {
        WorkplacementLWSDataRequest dataRequest = RequestHandler.remapToData(request, WorkplacementLWSDataRequest.class);
        return (T) updateWorkplacementervice.execute(dataRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonCreateWorkplacement">
    @Autowired
    @Qualifier("lawsonCreateWorkplacementService")
    private LawsonCreateWorkplacementService createWorkplacementervice;

    @Override
    public T createWorkplacement(Map<Object, Object> request) {
        WorkplacementLWSDataRequest dataRequest = RequestHandler.remapToData(request, WorkplacementLWSDataRequest.class);
        return (T) createWorkplacementervice.execute(dataRequest);
    }
    //</editor-fold>
}
