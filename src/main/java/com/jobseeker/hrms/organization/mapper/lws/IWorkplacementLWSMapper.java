package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataResponse;
import org.jobseeker.embedded.vacancy.lawson.LawsonWorkAreaEmbed;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IWorkplacementLWSMapper {

	WorkplacementLWSDataResponse toWorkplacementDataResponse(WorkplacementLawson workplacement);

	void toWriteWorkplacement(@MappingTarget WorkplacementLawson workplacement, WorkplacementLWSDataRequest request);

	LawsonWorkAreaEmbed toAttachWorkArea(WorkAreaLawson data);

}