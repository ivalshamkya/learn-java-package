package com.jobseeker.hrms.organization.service.basic.position.query;

import com.jobseeker.hrms.organization.data.basic.position.PositionDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IPositionMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.position.PositionOrgRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Position;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getPositionService")
public class GetPositionService {

	private final CompanyOrgRepository companyRepository;
	private final PositionOrgRepository positionOrgRepository;

	private final ICompanyMapper companyMapper;
	private final IPositionMapper positionMapper;

	private final Validator validator;

	public PositionDataResponse execute(String oid) {
		Position position = positionOrgRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
		return positionMapper.toPositionDataResponse(position);
	}
}
