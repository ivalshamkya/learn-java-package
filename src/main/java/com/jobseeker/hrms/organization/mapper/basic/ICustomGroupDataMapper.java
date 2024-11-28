package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.customData.CustomGroupResponse;
import org.jobseeker.organization.CustomGroupData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomGroupDataMapper {
	CustomGroupResponse toResponse(CustomGroupData customGroupData);
}
