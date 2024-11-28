package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.position.PositionDataRequest;
import com.jobseeker.hrms.organization.data.basic.position.PositionDataResponse;
import org.jobseeker.organization.Position;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IPositionMapper {

	PositionDataResponse toPositionDataResponse(Position position);
	void toWritePosition(@MappingTarget Position position, PositionDataRequest request);

}