package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataRequest;
import com.jobseeker.hrms.organization.data.basic.jobType.JobTypeDataResponse;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.JobType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IJobTypeMapper {

	JobTypeDataResponse toJobTypeDataResponse(JobType jobType);
	
	void toWriteJobType(@MappingTarget JobType jobType, JobTypeDataRequest request);

	GeneralDataEmbed toAttachJobType(JobType jobType);
}
