package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.emailTemplate.EmailTemplateDataResponse;
import org.jobseeker.organization.EmailTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmailTemplateMapper {

	EmailTemplateDataResponse toEmailTemplateDataResponse(EmailTemplate emailTemplate);
}
