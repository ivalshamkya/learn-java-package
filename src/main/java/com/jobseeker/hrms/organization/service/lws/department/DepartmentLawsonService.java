package com.jobseeker.hrms.organization.service.lws.department;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.service.baseContract.BaseDepartment;
import com.jobseeker.hrms.organization.service.lws.department.command.LawsonCreateDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.command.LawsonDeleteDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.command.LawsonUpdateDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.query.LawsonGetDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.query.LawsonGetDepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class DepartmentLawsonService<T> extends BaseDepartment<T> {

    //<editor-fold desc="lawsonGetDepartments">
    @Autowired
    @Qualifier("lawsonGetDepartmentsService")
    private LawsonGetDepartmentsService lawsonGetDepartmentsService;

    @Override
    public Page<T> getDepartments(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) lawsonGetDepartmentsService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonGetDepartment">
    @Autowired
    @Qualifier("lawsonGetDepartmentService")
    private LawsonGetDepartmentService getDepartmentService;

    @Override
    public T getDepartment(String oid) {
        return (T) getDepartmentService.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="lawsonDeleteDepartment">
    @Autowired
    @Qualifier("lawsonDeleteDepartmentService")
    private LawsonDeleteDepartmentService deleteDepartmentService;

    @Override
    public String deleteDepartment(String oid) {
        return deleteDepartmentService.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonUpdateDepartment">
    @Autowired
    @Qualifier("lawsonUpdateDepartmentService")
    private LawsonUpdateDepartmentService updateDepartmentService;

    @Override
    public T updateDepartment(Map<Object, Object> request, String oid) {
        DepartmentLWSDataRequest dataRequest = RequestHandler.remapToData(request, DepartmentLWSDataRequest.class);
        return (T) updateDepartmentService.execute(dataRequest, oid);
    }
    //</editor-fold>


    //<editor-fold desc="lawsonCreateDepartment">
    @Autowired
    @Qualifier("lawsonCreateDepartmentService")
    private LawsonCreateDepartmentService createDepartmentService;

    @Override
    public T createDepartment(Map<Object, Object> request) {
        DepartmentLWSDataRequest dataRequest = RequestHandler.remapToData(request, DepartmentLWSDataRequest.class);
        return (T) createDepartmentService.execute(dataRequest);
    }
    //</editor-fold>
}
