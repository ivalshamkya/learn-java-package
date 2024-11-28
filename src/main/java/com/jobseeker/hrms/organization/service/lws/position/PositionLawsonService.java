package com.jobseeker.hrms.organization.service.lws.position;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BasePosition;
import com.jobseeker.hrms.organization.service.lws.position.command.LawsonCreatePositionService;
import com.jobseeker.hrms.organization.service.lws.position.command.LawsonDeletePositionService;
import com.jobseeker.hrms.organization.service.lws.position.command.LawsonUpdatePositionService;
import com.jobseeker.hrms.organization.service.lws.position.query.LawsonGetPositionService;
import com.jobseeker.hrms.organization.service.lws.position.query.LawsonGetPositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class PositionLawsonService<T> extends BasePosition<T> {

    //<editor-fold desc="lawsonGetPositions">
    @Autowired
    @Qualifier("lawsonGetPositionsService")
    private LawsonGetPositionsService lawsonGetPositionsService;

    @Override
    public Page<T> getPositions(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) lawsonGetPositionsService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonGetPosition">
    @Autowired
    @Qualifier("lawsonGetPositionService")
    private LawsonGetPositionService getPositionService;

    @Override
    public T getPosition(String oid) {
        return (T) getPositionService.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonDeletePosition">
    @Autowired
    @Qualifier("lawsonDeletePositionService")
    private LawsonDeletePositionService deletePositionService;

    @Override
    public String deletePosition(String oid) {
        return deletePositionService.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonUpdatePosition">
    @Autowired
    @Qualifier("lawsonUpdatePositionService")
    private LawsonUpdatePositionService updatePositionService;

    @Override
    public T updatePosition(Map<Object, Object> request, String oid) {
        PositionLWSDataRequest dataRequest = RequestHandler.remapToData(request, PositionLWSDataRequest.class);
        return (T) updatePositionService.execute(dataRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonCreatePosition">
    @Autowired
    @Qualifier("lawsonCreatePositionService")
    private LawsonCreatePositionService createPositionService;

    @Override
    public T createPosition(Map<Object, Object> request) {
        PositionLWSDataRequest dataRequest = RequestHandler.remapToData(request, PositionLWSDataRequest.class);
        return (T) createPositionService.execute(dataRequest);
    }
    //</editor-fold>
}
