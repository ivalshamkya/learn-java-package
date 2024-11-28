package com.jobseeker.hrms.organization.service.basic.recruitmentStage;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseRecruitmentStage;
import com.jobseeker.hrms.organization.service.basic.recruitmentStage.command.CreateRecruitmentStage;
import com.jobseeker.hrms.organization.service.basic.recruitmentStage.command.DeleteRecruitmentStage;
import com.jobseeker.hrms.organization.service.basic.recruitmentStage.command.UpdateRecruitmentStage;
import com.jobseeker.hrms.organization.service.basic.recruitmentStage.query.GetRecruitmentStage;
import com.jobseeker.hrms.organization.service.basic.recruitmentStage.query.GetRecruitmentStages;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class RecruitmentStageService<T> extends BaseRecruitmentStage<T> {

	//<editor-fold desc="getRecruitmentStage">
	@Autowired
	@Qualifier("getRecruitmentStage")
	private GetRecruitmentStage getRecruitmentStage;

	@Override
	public T getRecruitmentStage(String param) {
		return (T) getRecruitmentStage.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="getRecruitmentStages">
	@Autowired
	@Qualifier("getRecruitmentStages")
	private GetRecruitmentStages getRecruitmentStages;

	@Override
	public Page<T> getRecruitmentStages(Map<Object, Object> param) {
		PaginationParam dataRequest = RequestHandler.remapToData(param, PaginationParam.class);
		return (Page<T>) getRecruitmentStages.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createRecruitmentStage">
	@Autowired
	@Qualifier("createRecruitmentStage")
	private CreateRecruitmentStage createRecruitmentStage;

	@Override
	public T createRecruitmentStage(Map<Object, Object> request) {
		RecruitmentStageDataRequest dataRequest = RequestHandler.remapToData(request, RecruitmentStageDataRequest.class);
		return (T) createRecruitmentStage.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateRecruitmentStage">
	@Autowired
	@Qualifier("updateRecruitmentStage")
	private UpdateRecruitmentStage updateRecruitmentStage;

	@Override
	public T updateRecruitmentStage(Map<Object, Object> request, String oid) {
		RecruitmentStageDataRequest dataRequest = RequestHandler.remapToData(request, RecruitmentStageDataRequest.class);
		return (T) updateRecruitmentStage.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteRecruitmentStage">
	@Autowired
	@Qualifier("deleteRecruitmentStage")
	private DeleteRecruitmentStage deleteRecruitmentStage;

	@Override
	public String deleteRecruitmentStage(String oid) {
		return deleteRecruitmentStage.execute(oid);
	}
	//</editor-fold>
}