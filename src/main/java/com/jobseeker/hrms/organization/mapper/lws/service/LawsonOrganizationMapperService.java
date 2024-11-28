package com.jobseeker.hrms.organization.mapper.lws.service;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.sms.organization.workArea.WorkAreaOrgSmsRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.lawson.PositionLawson;
import org.jobseeker.organization.sms.WorkAreaSMS;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class LawsonOrganizationMapperService {

	private final WorkAreaOrgSmsRepository workAreaOrgSmsRepository;

	@Named("lawsonSetPosition")
	public String setPosition(PositionLawson positionLawson) {
		return positionLawson.getName() + " - " + positionLawson.getGrade();
	}

}
