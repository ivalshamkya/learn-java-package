package com.jobseeker.hrms.organization.service.lws.recruitmentStage;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.recruitmentStage.RecruitmentStageDataLWSRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseRecruitmentStage;
import com.jobseeker.hrms.organization.service.lws.recruitmentStage.command.LawsonCreateRecruitmentStage;
import com.jobseeker.hrms.organization.service.lws.recruitmentStage.command.LawsonDeleteRecruitmentStage;
import com.jobseeker.hrms.organization.service.lws.recruitmentStage.command.LawsonUpdateRecruitmentStage;
import com.jobseeker.hrms.organization.service.lws.recruitmentStage.query.LawsonGetRecruitmentStage;
import com.jobseeker.hrms.organization.service.lws.recruitmentStage.query.LawsonGetRecruitmentStages;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class RecruitmentStageLWSService<T> extends BaseRecruitmentStage<T> {

	//<editor-fold desc="getRecruitmentStage">
	@Autowired
	@Qualifier("lawsonGetRecruitmentStage")
	private LawsonGetRecruitmentStage getRecruitmentStage;

	@Override
	public T getRecruitmentStage(String param) {
		return (T) getRecruitmentStage.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="getRecruitmentStages">
	@Autowired
	@Qualifier("lawsonGetRecruitmentStages")
	private LawsonGetRecruitmentStages getRecruitmentStages;

	@Override
	public Page<T> getRecruitmentStages(Map<Object, Object> param) {
		PaginationParam dataRequest = RequestHandler.remapToData(param, PaginationParam.class);
		return (Page<T>) getRecruitmentStages.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createRecruitmentStage">
	@Autowired
	@Qualifier("lawsonCreateRecruitmentStage")
	private LawsonCreateRecruitmentStage createRecruitmentStage;

	@Override
	public T createRecruitmentStage(Map<Object, Object> request) {
		RecruitmentStageDataLWSRequest dataRequest = RequestHandler.remapToData(request, RecruitmentStageDataLWSRequest.class);
		return (T) createRecruitmentStage.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateRecruitmentStage">
	@Autowired
	@Qualifier("lawsonUpdateRecruitmentStage")
	private LawsonUpdateRecruitmentStage updateRecruitmentStage;

	@Override
	public T updateRecruitmentStage(Map<Object, Object> request, String oid) {
		RecruitmentStageDataLWSRequest dataRequest = RequestHandler.remapToData(request, RecruitmentStageDataLWSRequest.class);
		return (T) updateRecruitmentStage.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteRecruitmentStage">
	@Autowired
	@Qualifier("lawsonDeleteRecruitmentStage")
	private LawsonDeleteRecruitmentStage deleteRecruitmentStage;

	@Override
	public String deleteRecruitmentStage(String oid) {
		return deleteRecruitmentStage.execute(oid);
	}
	//</editor-fold>
}