package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataResponse;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IWorkAreaLWSMapper {

	WorkAreaLWSDataResponse toWorkAreaLWSDataResponse(WorkAreaLawson workAreaLawson);

	void toWriteWorkArea(@MappingTarget WorkAreaLawson workplacement, WorkAreaLWSDataRequest request);

	GeneralDataEmbed toAttacBranch(Branch data);
}
