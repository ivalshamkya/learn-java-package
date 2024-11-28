package com.jobseeker.hrms.organization.service.lws.jobLevel;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobLevel;
import com.jobseeker.hrms.organization.service.lws.jobLevel.command.LawsonCreateJobLevel;
import com.jobseeker.hrms.organization.service.lws.jobLevel.command.LawsonDeleteJobLevel;
import com.jobseeker.hrms.organization.service.lws.jobLevel.command.LawsonUpdateJobLevel;
import com.jobseeker.hrms.organization.service.lws.jobLevel.query.LawsonGetJobLevel;
import com.jobseeker.hrms.organization.service.lws.jobLevel.query.LawsonGetJobLevels;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class JobLevelLWSService<T> extends BaseJobLevel<T> {

	//<editor-fold desc="getJobLevel">
	@Autowired
	@Qualifier("lawsonGetJobLevel")
	private LawsonGetJobLevel lawsonGetJobLevel;

	@Override
	public T getJobLevel(String param) {
		return (T) lawsonGetJobLevel.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="getJobLevels">
	@Autowired
	@Qualifier("lawsonGetJobLevels")
	private LawsonGetJobLevels lawsonGetJobLevels;

	@Override
	public Page<T> getJobLevels(Map<Object, Object> param) {
		PaginationParam dataRequest = RequestHandler.remapToData(param, PaginationParam.class);
		return (Page<T>) lawsonGetJobLevels.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createJobLevel">
	@Autowired
	@Qualifier("lawsonCreateJobLevel")
	private LawsonCreateJobLevel lawsonCreateJobLevel;

	@Override
	public T createJobLevel(Map<Object, Object> request) {
		JobLevelDataRequest dataRequest = RequestHandler.remapToData(request, JobLevelDataRequest.class);
		return (T) lawsonCreateJobLevel.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateJobLevel">
	@Autowired
	@Qualifier("lawsonUpdateJobLevel")
	private LawsonUpdateJobLevel lawsonUpdateJobLevel;

	@Override
	public T updateJobLevel(Map<Object, Object> request, String oid) {
		JobLevelDataRequest dataRequest = RequestHandler.remapToData(request, JobLevelDataRequest.class);
		return (T) lawsonUpdateJobLevel.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteJobLevel">
	@Autowired
	@Qualifier("lawsonDeleteJobLevel")
	private LawsonDeleteJobLevel lawsonDeleteJobLevel;

	@Override
	public String deleteJobLevel(String oid) {
		return lawsonDeleteJobLevel.execute(oid);
	}
	//</editor-fold>
}