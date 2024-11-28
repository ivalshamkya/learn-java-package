package com.jobseeker.hrms.organization.service.basic.jobType;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobType;
import com.jobseeker.hrms.organization.service.basic.jobType.command.CreateJobType;
import com.jobseeker.hrms.organization.service.basic.jobType.command.DeleteJobType;
import com.jobseeker.hrms.organization.service.basic.jobType.command.UpdateJobType;
import com.jobseeker.hrms.organization.service.basic.jobType.query.GetJobType;
import com.jobseeker.hrms.organization.service.basic.jobType.query.GetJobTypes;
import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class JobTypeService<T> extends BaseJobType<T> {

    //<editor-fold desc="getJobType">
    @Autowired
    @Qualifier("getJobType")
    private GetJobType getJobType;

    @Override
    public T getJobType(String param) {
        return (T) getJobType.execute(param);
    }
    //</editor-fold>

    //<editor-fold desc="getJobTypes">
    @Autowired
    @Qualifier("getJobTypes")
    private GetJobTypes getJobTypes;

    @Override
    public Page<T> getJobTypes(Map<Object, Object> param) {
        PaginationJobTypeParam dataRequest = RequestHandler.remapToData(param, PaginationJobTypeParam.class);
        return (Page<T>) getJobTypes.execute(dataRequest);
    }
    //</editor-fold>

    //<editor-fold desc="createJobType">
    @Autowired
    @Qualifier("createJobType")
    private CreateJobType createJobType;

    @Override
    public T createJobType(Map<Object, Object> request) {
        JobTypeDataRequest dataRequest = RequestHandler.remapToData(request, JobTypeDataRequest.class);
        return (T) createJobType.execute(dataRequest);
    }
    //</editor-fold>

    //<editor-fold desc="updateJobType">
    @Autowired
    @Qualifier("updateJobType")
    private UpdateJobType updateJobType;

    @Override
    public T updateJobType(Map<Object, Object> request, String oid) {
        JobTypeDataRequest dataRequest = RequestHandler.remapToData(request, JobTypeDataRequest.class);
        return (T) updateJobType.execute(dataRequest, oid);
    }
    //</editor-fold>

    //<editor-fold desc="deleteJobType">
    @Autowired
    @Qualifier("deleteJobType")
    private DeleteJobType deleteJobType;

    @Override
    public String deleteJobType(String oid) {
        return deleteJobType.execute(oid);
    }
    //</editor-fold>
}
