package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataRequest;
import com.jobseeker.hrms.organization.data.basic.jobLevel.JobLevelDataResponse;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.JobLevel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IJobLevelMapper {

	JobLevelDataResponse toJobLevelDataResponse(JobLevel jobLevel);
	void toWriteJobLevel(@MappingTarget JobLevel jobLevel, JobLevelDataRequest request);

	GeneralDataEmbed toAttachJobLevel(JobLevel jobLevel);

}