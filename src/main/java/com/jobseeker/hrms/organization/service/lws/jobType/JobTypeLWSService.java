package com.jobseeker.hrms.organization.service.lws.jobType;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataRequest;
import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobType;
import com.jobseeker.hrms.organization.service.lws.jobType.command.LawsonCreateJobType;
import com.jobseeker.hrms.organization.service.lws.jobType.command.LawsonDeleteJobType;
import com.jobseeker.hrms.organization.service.lws.jobType.command.LawsonUpdateJobType;
import com.jobseeker.hrms.organization.service.lws.jobType.query.LawsonGetJobType;
import com.jobseeker.hrms.organization.service.lws.jobType.query.LawsonGetJobTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class JobTypeLWSService<T> extends BaseJobType<T> {

    //<editor-fold desc="getJobType">
    @Autowired
    @Qualifier("lawsonGetJobType")
    private LawsonGetJobType lawsonGetJobType;

    @Override
    public T getJobType(String param) {
        return (T) lawsonGetJobType.execute(param);
    }
    //</editor-fold>

    //<editor-fold desc="getJobTypes">
    @Autowired
    @Qualifier("lawsonGetJobTypes")
    private LawsonGetJobTypes lawsonGetJobTypes;

    @Override
    public Page<T> getJobTypes(Map<Object, Object> param) {
        PaginationJobTypeParam dataRequest = RequestHandler.remapToData(param, PaginationJobTypeParam.class);
        return (Page<T>) lawsonGetJobTypes.execute(dataRequest);
    }
    //</editor-fold>

    //<editor-fold desc="createJobType">
    @Autowired
    @Qualifier("lawsonCreateJobType")
    private LawsonCreateJobType lawsonCreateJobType;

    @Override
    public T createJobType(Map<Object, Object> request) {
        JobTypeDataRequest dataRequest = RequestHandler.remapToData(request, JobTypeDataRequest.class);
        return (T) lawsonCreateJobType.execute(dataRequest);
    }
    //</editor-fold>

    //<editor-fold desc="updateJobType">
    @Autowired
    @Qualifier("lawsonUpdateJobType")
    private LawsonUpdateJobType lawsonUpdateJobType;

    @Override
    public T updateJobType(Map<Object, Object> request, String oid) {
        JobTypeDataRequest dataRequest = RequestHandler.remapToData(request, JobTypeDataRequest.class);
        return (T) lawsonUpdateJobType.execute(dataRequest, oid);
    }
    //</editor-fold>

    //<editor-fold desc="deleteJobType">
    @Autowired
    @Qualifier("lawsonDeleteJobType")
    private LawsonDeleteJobType lawsonDeleteJobType;

    @Override
    public String deleteJobType(String oid) {
        return lawsonDeleteJobType.execute(oid);
    }
    //</editor-fold>
}
