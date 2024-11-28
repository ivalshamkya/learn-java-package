package com.jobseeker.hrms.organization.service.lws.businessUnit.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IBusinessUnitLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.businessUnit.BusinessUnitOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.lawson.BusinessUnitLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetBusinessUnitService")
public class LawsonGetBusinessUnitService {

	private final BusinessUnitOrgLWSRepository repository;
	private final IBusinessUnitLWSMapper mapper;

	public BusinessUnitLWSDataResponse execute(String oid) {
		BusinessUnitLawson data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Work Area")));

		return mapper.toBusinessUnitLWSDataResponse(data);
	}
}
