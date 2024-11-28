package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.customData.CustomDataResponse;
import org.jobseeker.organization.CustomData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomDataMapper {
	CustomDataResponse toResponse(CustomData customData);
}
