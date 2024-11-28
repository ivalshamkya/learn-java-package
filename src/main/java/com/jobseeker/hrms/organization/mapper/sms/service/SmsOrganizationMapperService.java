package com.jobseeker.hrms.organization.mapper.sms.service;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.sms.ISmsBranchMapper;
import com.jobseeker.hrms.organization.mapper.sms.ISmsWorkAreaMapper;
import com.jobseeker.hrms.organization.repositories.sms.organization.workArea.WorkAreaOrgSmsRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.sms.WorkAreaSMS;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SmsOrganizationMapperService {

	private final WorkAreaOrgSmsRepository workAreaOrgSmsRepository;
	private final ISmsWorkAreaMapper smsWorkAreaMapper;

	@Named("smsSetWorkArea")
	public GeneralDataEmbed setWorkArea(String workAreaId) {
		if (workAreaId == null) return null;
		WorkAreaSMS data = workAreaOrgSmsRepository.findById(workAreaId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_WORK_AREA_FOUND.getMsg()));

		return smsWorkAreaMapper.toAttachWorkArea(data);
	}

}
