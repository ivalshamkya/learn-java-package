package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataRequest;
import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataResponse;
import org.jobseeker.organization.RecruitmentStage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IRecruitmentStageMapper {

	RecruitmentStageDataResponse toRecruitmentStageDataResponse(RecruitmentStage recruitmentStage);
	void toWriteRecruitmentStage(@MappingTarget RecruitmentStage recruitmentStage, RecruitmentStageDataRequest request);

}
