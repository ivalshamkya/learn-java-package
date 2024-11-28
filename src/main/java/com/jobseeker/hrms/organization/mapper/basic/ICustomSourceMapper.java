package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.customSource.CustomSourceDataResponse;
import org.jobseeker.organization.CustomSource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomSourceMapper {

	CustomSourceDataResponse toCustomSourceDataResponse(CustomSource customSource);
}
