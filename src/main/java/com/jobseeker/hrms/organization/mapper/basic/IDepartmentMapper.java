package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataRequest;
import com.jobseeker.hrms.organization.data.basic.department.DepartmentDataResponse;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IDepartmentMapper {

	DepartmentDataResponse toDepartmentDataResponse(Department department);
	void toWriteDepartment(@MappingTarget Department department, DepartmentDataRequest request);

	GeneralDataEmbed toAttachDepartment(Department department);

}
