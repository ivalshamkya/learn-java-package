package com.jobseeker.hrms.organization.service.lws.workplacement.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IWorkplacementLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.workArea.WorkAreaOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.workplacement.WorkplacementOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonUpdateWorkplacementLawsonService")
public class LawsonUpdateWorkplacementService {

	private final CompanyOrgRepository companyRepository;
	private final WorkAreaOrgLWSRepository workAreaOrgLWSRepository;
	private final WorkplacementOrgLWSRepository repository;

	private final IWorkplacementLWSMapper mapper;
	private final ICompanyMapper companyMapper;

	public WorkplacementLWSDataResponse execute(WorkplacementLWSDataRequest request, String oid) {
		WorkplacementLawson data = composeData(request, oid);

		return mapper.toWorkplacementDataResponse(data);
	}

	private WorkplacementLawson composeData(WorkplacementLWSDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
		WorkAreaLawson workArea = workAreaOrgLWSRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), request.getWorkAreaId()).orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Division")));

		WorkplacementLawson data = new WorkplacementLawson();

		if (oid != null) {
			data = repository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
			data.setWorkArea(mapper.toAttachWorkArea(workArea));
			data.setUpdatedAt(LocalDateTime.now());
		} else {
			data.setStatus(StatusData.ACTIVE);
			data.setCreatedAt(LocalDateTime.now());
		}

		data.setCompany(companyMapper.toAttachCompany(company));

		mapper.toWriteWorkplacement(data, request);
		return repository.save(data);
	}
}
