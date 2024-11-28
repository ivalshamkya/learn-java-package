package com.jobseeker.hrms.organization.service.lws.position.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IPositionLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.position.PositionOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.PositionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonUpdatePositionService")
public class LawsonUpdatePositionService {

	private final CompanyOrgRepository companyRepository;
	private final PositionOrgLWSRepository repository;

	private final ICompanyMapper companyMapper;
	private final IPositionLWSMapper mapper;

	public PositionLWSDataResponse execute(PositionLWSDataRequest request, String oid) {
		PositionLawson data = composeData(request, oid);

		return mapper.toPositionDataResponse(data);
	}

	private PositionLawson composeData(PositionLWSDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

		PositionLawson data = new PositionLawson();
		if (oid != null) {
			data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
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
