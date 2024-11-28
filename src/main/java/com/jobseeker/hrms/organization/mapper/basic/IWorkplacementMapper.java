package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataRequest;
import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataResponse;
import org.jobseeker.organization.Workplacement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IWorkplacementMapper {

	WorkplacementDataResponse toWorkplacementDataResponse(Workplacement workplacement);
	void toWriteWorkplacement(@MappingTarget Workplacement workplacement, WorkplacementDataRequest request);

}