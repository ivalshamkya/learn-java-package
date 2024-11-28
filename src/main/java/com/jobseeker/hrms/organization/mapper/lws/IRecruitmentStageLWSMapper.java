package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataRequest;
import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataResponse;
import com.jobseeker.hrms.organization.data.lws.recruitmentStage.RecruitmentStageDataLWSRequest;
import org.jobseeker.organization.RecruitmentStage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IRecruitmentStageLWSMapper {

	RecruitmentStageDataResponse toRecruitmentStageDataResponse(RecruitmentStage recruitmentStage);
	void toWriteRecruitmentStage(@MappingTarget RecruitmentStage recruitmentStage, RecruitmentStageDataLWSRequest request);

}
