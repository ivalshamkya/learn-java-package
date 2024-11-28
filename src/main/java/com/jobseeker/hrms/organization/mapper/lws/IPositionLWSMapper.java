package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.GeneralMapperService;
import com.jobseeker.hrms.organization.mapper.lws.service.LawsonOrganizationMapperService;
import org.jobseeker.organization.lawson.PositionLawson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
		GeneralMapperService.class, LawsonOrganizationMapperService.class

})
public interface IPositionLWSMapper {

	@Mapping(source = "jobFunction", target = "jobFunction", qualifiedByName = "setMultiLangToDataResponse")
	@Mapping(source = ".", target = "name", qualifiedByName = "lawsonSetPosition")
	PositionLWSDataResponse toPositionDataResponse(PositionLawson position);

	void toWritePosition(@MappingTarget PositionLawson position, PositionLWSDataRequest request);
}