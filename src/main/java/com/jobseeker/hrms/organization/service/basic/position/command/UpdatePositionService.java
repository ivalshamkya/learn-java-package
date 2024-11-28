package com.jobseeker.hrms.organization.service.basic.position.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.position.PositionDataRequest;
import com.jobseeker.hrms.organization.data.basic.position.PositionDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IPositionMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.position.PositionOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.Position;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonUpdatePositionService")
public class UpdatePositionService {

	private final CompanyOrgRepository companyRepository;
	private final PositionOrgRepository positionOrgRepository;

	private final ICompanyMapper companyMapper;
	private final IPositionMapper positionMapper;

	public PositionDataResponse execute(PositionDataRequest request, String oid) {
		Position position = composeBranch(request, oid);

		return positionMapper.toPositionDataResponse(position);
	}

	private Position composeBranch(PositionDataRequest request, String oid) {
		Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

		Position position = new Position();
		if (oid != null) {
			position = positionOrgRepository.findFirstByActive(oid)
					.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
			position.setUpdatedAt(LocalDateTime.now());
		} else {
			position.setStatus(StatusData.ACTIVE);
			position.setCreatedAt(LocalDateTime.now());
		}

		position.setCompany(companyMapper.toAttachCompany(company));

		positionMapper.toWritePosition(position, request);
		return positionOrgRepository.save(position);
	}
}
