package com.jobseeker.hrms.organization.service.basic.workplacement;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataRequest;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseWorkplacement;
import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataResponse;
import com.jobseeker.hrms.organization.service.basic.workplacement.command.CreateWorkplacement;
import com.jobseeker.hrms.organization.service.basic.workplacement.command.DeleteWorkplacement;
import com.jobseeker.hrms.organization.service.basic.workplacement.command.UpdateWorkplacement;
import com.jobseeker.hrms.organization.service.basic.workplacement.query.GetWorkplacement;
import com.jobseeker.hrms.organization.service.basic.workplacement.query.GetWorkplacements;
import com.jobseeker.hrms.organization.service.lws.workArea.command.LawsonCreateWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.command.LawsonDeleteWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.command.LawsonUpdateWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.query.LawsonGetWorkAreaService;
import com.jobseeker.hrms.organization.service.lws.workArea.query.LawsonGetWorkAreasService;
import com.jobseeker.hrms.organization.service.lws.workplacement.command.LawsonCreateWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.command.LawsonDeleteWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.command.LawsonUpdateWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.query.LawsonGetWorkplacementService;
import com.jobseeker.hrms.organization.service.lws.workplacement.query.LawsonGetWorkplacementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class WorkplacementService<T> extends BaseWorkplacement<T> {

    //<editor-fold desc="getWorkplacements">
    @Autowired
    @Qualifier("getWorkplacements")
    private GetWorkplacements getWorkplacements;

    @Override
    public Page<T> getWorkplacements(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) getWorkplacements.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="getWorkplacement">
    @Autowired
    @Qualifier("getWorkplacement")
    private GetWorkplacement getWorkplacement;

    @Override
    public T getWorkplacement(String oid) {
        return (T) getWorkplacement.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="deleteWorkplacement">
    @Autowired
    @Qualifier("deleteWorkplacement")
    private DeleteWorkplacement deleteWorkplacement;

    @Override
    public String deleteWorkplacement(String oid) {
        return deleteWorkplacement.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="updateWorkplacement">
    @Autowired
    @Qualifier("updateWorkplacement")
    private UpdateWorkplacement updateWorkplacement;

    @Override
    public T updateWorkplacement(Map<Object, Object> request, String oid) {
        WorkplacementDataRequest objectRequest = RequestHandler.remapToData(request, WorkplacementDataRequest.class);
        return (T) updateWorkplacement.execute(objectRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonCreateWorkplacement">
    @Autowired
    @Qualifier("createWorkplacement")
    private CreateWorkplacement createWorkplacementervice;

    @Override
    public T createWorkplacement(Map<Object, Object> request) {
        WorkplacementDataRequest dataRequest = RequestHandler.remapToData(request, WorkplacementDataRequest.class);
        return (T) createWorkplacementervice.execute(dataRequest);
    }
    //</editor-fold>
}