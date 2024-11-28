package com.jobseeker.hrms.organization.service.lws.workArea;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseWorkArea;
import com.jobseeker.hrms.organization.service.lws.workArea.command.LawsonCreateWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.command.LawsonDeleteWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.command.LawsonUpdateWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.query.LawsonGetWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.query.LawsonGetWorkAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class WorkAreaLawsonService<T> extends BaseWorkArea<T> {

    //<editor-fold desc="lawsonGetWorkAreas">
    @Autowired
    @Qualifier("lawsonGetWorkAreasService")
    private LawsonGetWorkAreasService lawsonGetWorkAreasService;

    @Override
    public Page<T> getWorkAreas(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) lawsonGetWorkAreasService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonGetWorkArea">
    @Autowired
    @Qualifier("lawsonGetWorkAreaService")
    private LawsonGetWorkAreaService getWorkAreaService;

    @Override
    public T getWorkArea(String oid) {
        return (T) getWorkAreaService.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonDeleteWorkArea">
    @Autowired
    @Qualifier("lawsonDeleteWorkAreaService")
    private LawsonDeleteWorkAreaService deleteWorkAreaService;

    @Override
    public String deleteWorkArea(String oid) {
        return deleteWorkAreaService.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonUpdateWorkArea">
    @Autowired
    @Qualifier("lawsonUpdateWorkAreaService")
    private LawsonUpdateWorkAreaService updateWorkAreaService;

    @Override
    public T updateWorkArea(Map<Object, Object> request, String oid) {
        WorkAreaLWSDataRequest dataRequest = RequestHandler.remapToData(request, WorkAreaLWSDataRequest.class);
        return (T) updateWorkAreaService.execute(dataRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonCreateWorkArea">
    @Autowired
    @Qualifier("lawsonCreateWorkAreaService")
    private LawsonCreateWorkAreaService createWorkAreaService;

    @Override
    public T createWorkArea(Map<Object, Object> request) {
        WorkAreaLWSDataRequest dataRequest = RequestHandler.remapToData(request, WorkAreaLWSDataRequest.class);
        return (T) createWorkAreaService.execute(dataRequest);
    }
    //</editor-fold>
}
