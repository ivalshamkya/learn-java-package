package com.jobseeker.hrms.organization.interfaces;

import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IRecruitmentStageHandler<T> {
	Page<T> getRecruitmentStages(Map<Object, Object> param);

	T getRecruitmentStage(String oid);

	T createRecruitmentStage(Map<Object, Object> request) throws Exception;

	T updateRecruitmentStage(Map<Object, Object> request, String oid) throws Exception;

	String deleteRecruitmentStage(String oid) throws Exception;
}