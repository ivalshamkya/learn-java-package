package com.jobseeker.hrms.organization.service.lws.workArea.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IWorkAreaLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.workArea.WorkAreaOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetWorkAreaService")
public class LawsonGetWorkAreaService {

	private final WorkAreaOrgLWSRepository repository;
	private final IWorkAreaLWSMapper mapper;

	public WorkAreaLWSDataResponse execute(String oid) {
		WorkAreaLawson data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Work Area")));

		return mapper.toWorkAreaLWSDataResponse(data);
	}
}
