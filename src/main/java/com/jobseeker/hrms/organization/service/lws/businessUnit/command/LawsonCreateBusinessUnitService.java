package com.jobseeker.hrms.organization.service.lws.businessUnit.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IBusinessUnitLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.businessUnit.BusinessUnitOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.BusinessUnitLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonCreateBusinessUnitService")
public class LawsonCreateBusinessUnitService {
	private final CompanyOrgRepository companyRepository;
	private final BusinessUnitOrgLWSRepository departmentRepository;

	private final ICompanyMapper companyMapper;
	private final IBusinessUnitLWSMapper mapper;

	public BusinessUnitLWSDataResponse execute(BusinessUnitLWSDataRequest request) {
		BusinessUnitLawson data = composeData(request, null);

		return mapper.toBusinessUnitLWSDataResponse(data);
	}

	private BusinessUnitLawson composeData(BusinessUnitLWSDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

		BusinessUnitLawson data = new BusinessUnitLawson();
		if (oid != null) {
			data = departmentRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
			data.setUpdatedAt(LocalDateTime.now());
		} else {
			data.setStatus(StatusData.ACTIVE);
			data.setCreatedAt(LocalDateTime.now());
		}

		data.setCompany(companyMapper.toAttachCompany(company));

		mapper.toWriteBusinessUnit(data, request);
		return departmentRepository.save(data);
	}
}
