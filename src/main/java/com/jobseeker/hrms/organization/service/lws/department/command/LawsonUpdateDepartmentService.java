package com.jobseeker.hrms.organization.service.lws.department.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IDepartmentLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.department.DepartmentLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.division.DivisionLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.DepartmentLawson;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonUpdateDepartmentService")
public class LawsonUpdateDepartmentService {

	private final CompanyOrgRepository companyRepository;
	private final DepartmentLWSRepository departmentRepository;
	private final DivisionLWSRepository divisionRepository;

	private final ICompanyMapper companyMapper;
	private final IDepartmentLWSMapper departmentMapper;

	public DepartmentLWSDataResponse execute(DepartmentLWSDataRequest request, String oid) {
		DepartmentLawson department = composeData(request, oid);

		return departmentMapper.toDepartmentDataResponse(department);
	}

	private DepartmentLawson composeData(DepartmentLWSDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
		DivisionLawson division = divisionRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), request.getDivisionId()).orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Division")));

		DepartmentLawson data = new DepartmentLawson();
		if (oid != null) {
			data = departmentRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
			data.setDivision(departmentMapper.toAttachDivision(division));
			data.setUpdatedAt(LocalDateTime.now());
		} else {
			data.setDivision(departmentMapper.toAttachDivision(division));
			data.setStatus(StatusData.ACTIVE);
			data.setCreatedAt(LocalDateTime.now());
		}

		data.setCompany(companyMapper.toAttachCompany(company));

		departmentMapper.toWriteDepartment(data, request);
		return departmentRepository.save(data);
	}
}
