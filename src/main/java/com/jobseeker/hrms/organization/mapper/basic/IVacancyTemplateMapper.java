package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataPagingResponse;
import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataRequest;
import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.VacancyTemplateMapperService;
import org.jobseeker.organization.sms.VacancyTemplateSMS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = VacancyTemplateMapperService.class)
public interface IVacancyTemplateMapper {

	JobTemplateDataPagingResponse toJobTemplatePagingResponse(VacancyTemplateSMS vacancyTemplateSMS);

	JobTemplateDataResponse toJobTemplateResponse(VacancyTemplateSMS vacancyTemplateSMS);

	@Mapping(source = "departmentId", target = "department", qualifiedByName = "setDepartment")
	@Mapping(source = "jobTypeId", target = "jobType", qualifiedByName = "setJobType")
	@Mapping(source = "jobLevelId", target = "jobLevel", qualifiedByName = "setJobLevel")
	void toWriteVacancyTemplateSMS(@MappingTarget VacancyTemplateSMS vacancyTemplateSMS, JobTemplateDataRequest request);

}
