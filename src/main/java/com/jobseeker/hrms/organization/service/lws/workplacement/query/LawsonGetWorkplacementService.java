package com.jobseeker.hrms.organization.service.lws.workplacement.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IWorkplacementLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.workplacement.WorkplacementOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetDepartmentService")
public class LawsonGetWorkplacementService {

	private final WorkplacementOrgLWSRepository repository;
	private final IWorkplacementLWSMapper mapper;

	public WorkplacementLWSDataResponse execute(String oid) {
		WorkplacementLawson data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Workplacement")));

		return mapper.toWorkplacementDataResponse(data);
	}
}
