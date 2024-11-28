package com.jobseeker.hrms.organization.service.basic.department;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseDepartment;
import com.jobseeker.hrms.organization.service.basic.department.command.CreateDepartmentService;
import com.jobseeker.hrms.organization.service.basic.department.command.DeleteDepartmentService;
import com.jobseeker.hrms.organization.service.basic.department.command.UpdateDepartmentService;
import com.jobseeker.hrms.organization.service.basic.department.query.GetDepartmentService;
import com.jobseeker.hrms.organization.service.basic.department.query.GetDepartmentsService;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
//@RequiredArgsConstructor
public class DepartmentBasicService<T> extends BaseDepartment<T> {

    //<editor-fold desc="getDepartments">
    @Autowired
    @Qualifier("getDepartmentsService")
    private GetDepartmentsService getDepartmentsService;

    @Override
    public Page<T> getDepartments(Map<Object, Object> param) {
        PaginationParam paginationParam = RequestHandler.remapToData(param, PaginationParam.class);
        return (Page<T>) getDepartmentsService.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="getDepartment">
    @Autowired
    @Qualifier("getDepartmentService")
    private GetDepartmentService getDepartmentService;

    @Override
    public T getDepartment(String oid) {
        return (T) getDepartmentService.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="deleteDepartment">
    @Autowired
    @Qualifier("deleteDepartmentService")
    private DeleteDepartmentService deleteDepartmentService;

    @Override
    public String deleteDepartment(String oid) {
        return deleteDepartmentService.execute(oid);
    }
    //</editor-fold>


    //<editor-fold desc="updateDepartment">
    @Autowired
    @Qualifier("updateDepartmentService")
    private UpdateDepartmentService updateDepartmentService;

    @Override
    public T updateDepartment(Map<Object, Object> request, String oid) {
        DepartmentDataRequest dataRequest = RequestHandler.remapToData(request, DepartmentDataRequest.class);
        return (T) updateDepartmentService.execute(dataRequest, oid);
    }
    //</editor-fold>



    //<editor-fold desc="updateDepartment">
    @Autowired
    @Qualifier("createDepartmentService")
    private CreateDepartmentService createDepartmentService;

    @Override
    public T createDepartment(Map<Object, Object> request) {
        DepartmentDataRequest dataRequest = RequestHandler.remapToData(request, DepartmentDataRequest.class);
        return (T) createDepartmentService.execute(dataRequest);
    }
    //</editor-fold>
}
