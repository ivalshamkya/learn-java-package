package com.jobseeker.hrms.organization.service.basic.position;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.position.PositionDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BasePosition;
import com.jobseeker.hrms.organization.service.basic.position.command.CreatePositionService;
import com.jobseeker.hrms.organization.service.basic.position.command.DeletePositionService;
import com.jobseeker.hrms.organization.service.basic.position.command.UpdatePositionService;
import com.jobseeker.hrms.organization.service.basic.position.query.GetPositionService;
import com.jobseeker.hrms.organization.service.basic.position.query.GetPositionsService;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class PositionBasicService<T> extends BasePosition<T> {

    //<editor-fold desc="getPositions">
    @Autowired
    @Qualifier("getPositionsService")
    private GetPositionsService getPositionsService;

    @Override
    public Page<T> getPositions(Map<Object, Object> param) {
        PaginationParam paginationParam = RequestHandler.remapToData(param, PaginationParam.class);
        return (Page<T>) getPositionsService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="getPosition">
    @Autowired
    @Qualifier("getPositionService")
    private GetPositionService getPositionService;

    @Override
    public T getPosition(String oid) {
        return (T) getPositionService.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="DeletePosition">
    @Autowired
    @Qualifier("deletePositionService")
    private DeletePositionService deletePositionService;

    @Override
    public String deletePosition(String oid) {
        return deletePositionService.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="UpdatePosition">
    @Autowired
    @Qualifier("updatePositionService")
    private UpdatePositionService updatePositionService;

    @Override
    public T updatePosition(Map<Object, Object> request, String oid) {
        PositionDataRequest dataRequest = RequestHandler.remapToData(request, PositionDataRequest.class);
        return (T) updatePositionService.execute(dataRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="CreatePosition">
    @Autowired
    @Qualifier("createPositionService")
    private CreatePositionService createPositionService;

    @Override
    public T createPosition(Map<Object, Object> request) {
        PositionDataRequest dataRequest = RequestHandler.remapToData(request, PositionDataRequest.class);
        return (T) createPositionService.execute(dataRequest);
    }
    //</editor-fold>
}
