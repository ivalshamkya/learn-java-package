package com.jobseeker.hrms.organization.mapper.basic.service;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.sms.organization.workArea.WorkAreaOrgSmsRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.sms.WorkAreaSMS;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class OrganizationMapperService {

	private final WorkAreaOrgSmsRepository workAreaOrgSmsRepository;

	@Named("setWorkArea")
	public GeneralDataEmbed setWorkArea(String workAreaId) {
		if (workAreaId == null) return null;
        WorkAreaSMS workAreaSMS = workAreaOrgSmsRepository.findById(workAreaId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_WORK_AREA_FOUND.getMsg()));

		return new GeneralDataEmbed(workAreaSMS.get_id(), workAreaSMS.getName());
	}

}
