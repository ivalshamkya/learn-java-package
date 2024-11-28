package com.jobseeker.hrms.organization.service.lws.workArea.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IWorkAreaLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.workArea.WorkAreaOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonCreateWorkAreaService")
public class LawsonCreateWorkAreaService {

	private final CompanyOrgRepository companyRepository;
	private final WorkAreaOrgLWSRepository departmentRepository;
	private final BranchOrgRepository branchOrgRepository;

	private final ICompanyMapper companyMapper;
	private final IWorkAreaLWSMapper mapper;


	public WorkAreaLWSDataResponse execute(WorkAreaLWSDataRequest request) {
		WorkAreaLawson data = composeData(request, null);

		return mapper.toWorkAreaLWSDataResponse(data);
	}

	private WorkAreaLawson composeData(WorkAreaLWSDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
		Branch branch = branchOrgRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), request.getBranchId()).orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Division")));

		WorkAreaLawson data = new WorkAreaLawson();
		if (oid != null) {
			data = departmentRepository.findByCompanyAndIdActive(ServletRequestAWS.getCompanyId(), oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
			data.setBranch(mapper.toAttacBranch(branch));
			data.setUpdatedAt(LocalDateTime.now());
		} else {
			data.setBranch(mapper.toAttacBranch(branch));
			data.setStatus(StatusData.ACTIVE);
			data.setCreatedAt(LocalDateTime.now());
		}

		data.setCompany(companyMapper.toAttachCompany(company));

		mapper.toWriteWorkArea(data, request);
		return departmentRepository.save(data);
	}
}
