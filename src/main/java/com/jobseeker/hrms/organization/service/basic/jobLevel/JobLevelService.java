package com.jobseeker.hrms.organization.service.basic.jobLevel;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobLevel;
import com.jobseeker.hrms.organization.service.basic.jobLevel.command.CreateJobLevel;
import com.jobseeker.hrms.organization.service.basic.jobLevel.command.DeleteJobLevel;
import com.jobseeker.hrms.organization.service.basic.jobLevel.command.UpdateJobLevel;
import com.jobseeker.hrms.organization.service.basic.jobLevel.query.GetJobLevel;
import com.jobseeker.hrms.organization.service.basic.jobLevel.query.GetJobLevels;
import org.jobseeker.data.PaginationParam;
import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class JobLevelService<T> extends BaseJobLevel<T> {

	//<editor-fold desc="getJobLevel">
	@Autowired
	@Qualifier("getJobLevel")
	private GetJobLevel getJobLevel;

	@Override
	public T getJobLevel(String param) {
		return (T) getJobLevel.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="getJobLevels">
	@Autowired
	@Qualifier("getJobLevels")
	private GetJobLevels getJobLevels;

	@Override
	public Page<T> getJobLevels(Map<Object, Object> param) {
		PaginationParam dataRequest = RequestHandler.remapToData(param, PaginationParam.class);
		return (Page<T>) getJobLevels.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createJobLevel">
	@Autowired
	@Qualifier("createJobLevel")
	private CreateJobLevel createJobLevel;

	@Override
	public T createJobLevel(Map<Object, Object> request) {
		JobLevelDataRequest dataRequest = RequestHandler.remapToData(request, JobLevelDataRequest.class);
		return (T) createJobLevel.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateJobLevel">
	@Autowired
	@Qualifier("updateJobLevel")
	private UpdateJobLevel updateJobLevel;

	@Override
	public T updateJobLevel(Map<Object, Object> request, String oid) {
		JobLevelDataRequest dataRequest = RequestHandler.remapToData(request, JobLevelDataRequest.class);
		return (T) updateJobLevel.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteJobLevel">
	@Autowired
	@Qualifier("deleteJobLevel")
	private DeleteJobLevel deleteJobLevel;

	@Override
	public String deleteJobLevel(String oid) {
		return deleteJobLevel.execute(oid);
	}
	//</editor-fold>
}