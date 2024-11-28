package com.jobseeker.hrms.organization.service.basic.position.query;

import com.jobseeker.hrms.organization.data.basic.position.PositionDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IPositionMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.position.PositionOrgRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getPositionsService")
public class GetPositionsService {

	private final CompanyOrgRepository companyRepository;
	private final PositionOrgRepository positionOrgRepository;

	private final ICompanyMapper companyMapper;
	private final IPositionMapper positionMapper;

	private final Validator validator;

	public Page<PositionDataResponse> execute(PaginationParam param) {
		return positionOrgRepository.findByDataTables(param)
				.map(positionMapper::toPositionDataResponse);
	}

}
