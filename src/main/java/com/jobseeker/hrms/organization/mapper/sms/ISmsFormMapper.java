package com.jobseeker.hrms.organization.mapper.sms;


import com.jobseeker.hrms.organization.data.sms.form.FormDataResponse;
import org.jobseeker.organization.sms.FormSMS;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISmsFormMapper {
    FormDataResponse toResponse(FormSMS form);
}
