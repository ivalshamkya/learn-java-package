package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataResponse;
import org.jobseeker.organization.lawson.BusinessUnitLawson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IBusinessUnitLWSMapper {

	BusinessUnitLWSDataResponse toBusinessUnitLWSDataResponse(BusinessUnitLawson businessUnitLawson);

	void toWriteBusinessUnit(@MappingTarget BusinessUnitLawson workplacement, BusinessUnitLWSDataRequest request);

}
