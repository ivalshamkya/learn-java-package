package com.jobseeker.hrms.organization.service.lws.businessUnit;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.service.baseContract.BaseBusinessUnit;
import com.jobseeker.hrms.organization.service.lws.businessUnit.command.LawsonCreateBusinessUnitService;
import com.jobseeker.hrms.organization.service.lws.businessUnit.command.LawsonDeleteBusinessUnitService;
import com.jobseeker.hrms.organization.service.lws.businessUnit.command.LawsonUpdateBusinessUnitService;
import com.jobseeker.hrms.organization.service.lws.businessUnit.query.LawsonGetBusinessUnitService;
import com.jobseeker.hrms.organization.service.lws.businessUnit.query.LawsonGetBusinessUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class BusinessUnitLawsonService<T> extends BaseBusinessUnit<T> {

    //<editor-fold desc="lawsonGetBusinessUnits">
    @Autowired
    @Qualifier("lawsonGetBusinessUnitsService")
    private LawsonGetBusinessUnitsService lawsonGetBusinessUnitsService;

    @Override
    public Page<T> getBusinessUnits(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) lawsonGetBusinessUnitsService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonGetBusinessUnit">
    @Autowired
    @Qualifier("lawsonGetBusinessUnitService")
    private LawsonGetBusinessUnitService getBusinessUnitService;

    @Override
    public T getBusinessUnit(String oid) {
        return (T) getBusinessUnitService.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonDeleteBusinessUnit">
    @Autowired
    @Qualifier("lawsonDeleteBusinessUnitService")
    private LawsonDeleteBusinessUnitService deleteBusinessUnitService;

    @Override
    public String deleteBusinessUnit(String oid) {
        return deleteBusinessUnitService.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonUpdateBusinessUnit">
    @Autowired
    @Qualifier("lawsonUpdateBusinessUnitService")
    private LawsonUpdateBusinessUnitService updateBusinessUnitService;

    @Override
    public T updateBusinessUnit(Map<Object, Object> request, String oid) {
        BusinessUnitLWSDataRequest dataRequest = RequestHandler.remapToData(request, BusinessUnitLWSDataRequest.class);
        return (T) updateBusinessUnitService.execute(dataRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonCreateBusinessUnit">
    @Autowired
    @Qualifier("lawsonCreateBusinessUnitService")
    private LawsonCreateBusinessUnitService createBusinessUnitService;

    @Override
    public T createBusinessUnit(Map<Object, Object> request) {
        BusinessUnitLWSDataRequest dataRequest = RequestHandler.remapToData(request, BusinessUnitLWSDataRequest.class);
        return (T) createBusinessUnitService.execute(dataRequest);
    }
    //</editor-fold>
}
