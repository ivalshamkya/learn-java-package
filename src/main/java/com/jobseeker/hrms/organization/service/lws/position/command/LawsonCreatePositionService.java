package com.jobseeker.hrms.organization.service.lws.position.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IMasterMapper;
import com.jobseeker.hrms.organization.mapper.lws.IPositionLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.position.PositionOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.master.JobFunction;
import org.jobseeker.master.repositories.JobFunctionMasterRepository;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.PositionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonCreatePositionService")
public class LawsonCreatePositionService {
	private final CompanyOrgRepository companyRepository;
	private final JobFunctionMasterRepository jobFunctionRepository;
	private final PositionOrgLWSRepository repository;

	private final ICompanyMapper companyMapper;
	private final IPositionLWSMapper mapper;
	private final IMasterMapper masterMapper;

	public PositionLWSDataResponse execute(PositionLWSDataRequest request) {
		PositionLawson data = composeData(request, null);

		return mapper.toPositionDataResponse(data);
	}

	private PositionLawson composeData(PositionLWSDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

		JobFunction jobFunction = jobFunctionRepository.findById(request.getJobFunctionId())
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(),"Job Function")));

		PositionLawson data = new PositionLawson();
		data.setJobFunction(masterMapper.toAttachJobFunctionDataEmbed(jobFunction));
		if (oid != null) {
			data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
					.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Position")));
			data.setUpdatedAt(LocalDateTime.now());
		} else {
			data.setStatus(StatusData.ACTIVE);
			data.setCreatedAt(LocalDateTime.now());
		}

		data.setCompany(companyMapper.toAttachCompany(company));

		mapper.toWritePosition(data, request);
		return repository.save(data);
	}
}
