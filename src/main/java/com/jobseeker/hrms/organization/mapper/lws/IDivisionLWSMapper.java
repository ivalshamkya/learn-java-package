package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataResponse;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IDivisionLWSMapper {

	DivisionLWSDataResponse toDivisionLWSDataResponse(DivisionLawson divisionLawson);

	void toWriteDivisionLawson(@MappingTarget DivisionLawson divisionLawson, DivisionLWSDataRequest request);

}
