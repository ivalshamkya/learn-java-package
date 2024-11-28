package com.jobseeker.hrms.organization.mapper.sms;

import com.jobseeker.hrms.organization.data.sms.workArea.WorkAreaSmsDataResponse;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.sms.WorkAreaSMS;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISmsWorkAreaMapper {

	WorkAreaSmsDataResponse toWorkAreaSmsDataResponse(WorkAreaSMS workAreaSMS);

	GeneralDataEmbed toAttachWorkArea(WorkAreaSMS workAreaSMS);
}
