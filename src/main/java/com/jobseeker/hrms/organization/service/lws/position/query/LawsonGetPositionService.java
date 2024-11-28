package com.jobseeker.hrms.organization.service.lws.position.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IPositionLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.position.PositionOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.lawson.PositionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetPositionService")
public class LawsonGetPositionService {

	private final PositionOrgLWSRepository repository;
	private final IPositionLWSMapper mapper;

	public PositionLWSDataResponse execute(String oid) {
		PositionLawson data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Work Area")));

		return mapper.toPositionDataResponse(data);
	}
}
