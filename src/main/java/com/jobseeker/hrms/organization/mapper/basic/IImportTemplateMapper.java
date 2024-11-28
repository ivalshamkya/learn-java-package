package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.importTemplate.ImportTemplateDataResponse;
import org.jobseeker.organization.ImportTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IImportTemplateMapper {

	ImportTemplateDataResponse toImportTemplateDataResponse(ImportTemplate emailTemplate);
}
