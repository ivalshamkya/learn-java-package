package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IApprovalHandler;
import com.jobseeker.hrms.organization.interfaces.IRecruitmentStageHandler;
import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseRecruitmentStage<T> implements IRecruitmentStageHandler<T> {

	@Override
	public Page<T> getRecruitmentStages(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getRecruitmentStage(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T createRecruitmentStage(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateRecruitmentStage(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteRecruitmentStage(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
