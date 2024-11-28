package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataResponse;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.lawson.DepartmentLawson;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IDepartmentLWSMapper {
	IDepartmentLWSMapper INSTANCE = Mappers.getMapper(IDepartmentLWSMapper.class);
	DepartmentLWSDataResponse toDepartmentDataResponse(DepartmentLawson department);

	void toWriteDepartment(@MappingTarget DepartmentLawson department, DepartmentLWSDataRequest request);

	GeneralDataEmbed toAttachDivision(DivisionLawson data);

}
