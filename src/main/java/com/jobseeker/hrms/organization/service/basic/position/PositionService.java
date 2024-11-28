package com.jobseeker.hrms.organization.service.basic.position;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.position.PositionDataRequest;
import com.jobseeker.hrms.organization.data.basic.position.PositionDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IPositionMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.position.PositionOrgRepository;
import com.jobseeker.hrms.organization.service.baseContract.BasePosition;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.Position;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PositionService extends BasePosition<PositionDataResponse> {

	private final CompanyOrgRepository companyRepository;
	private final PositionOrgRepository positionOrgRepository;

	private final ICompanyMapper companyMapper;
	private final IPositionMapper positionMapper;

	private final Validator validator;

	@Override
	public Page<PositionDataResponse> getPositions(Map<Object, Object> param) {
		PaginationParam paginationParam = RequestHandler.remapToData(param, PaginationParam.class);
		return positionOrgRepository.findByDataTables(paginationParam)
				.map(positionMapper::toPositionDataResponse);
	}

	@Override
	public PositionDataResponse getPosition(String oid) {
		Position position = positionOrgRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
		return positionMapper.toPositionDataResponse(position);
	}

	@Override
	public PositionDataResponse createPosition(Map<Object, Object> request) {
		PositionDataRequest objectRequest = getPositionRequest(request);
		Position position = composeBranch(objectRequest, null);

		return positionMapper.toPositionDataResponse(position);
	}

	@Override
	public PositionDataResponse updatePosition(Map<Object, Object> request, String oid) {
		PositionDataRequest objectRequest = getPositionRequest(request);
		Position position = composeBranch(objectRequest, oid);

		return positionMapper.toPositionDataResponse(position);
	}

	@Override
	public String deletePosition(String oid) {
		Position position = positionOrgRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
		position.setStatus(StatusData.DELETED);
		position.setDeletedAt(LocalDateTime.now());

		positionOrgRepository.save(position);
		return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
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

	private PositionDataRequest getPositionRequest(Map<Object, Object> request) {
		ObjectMapper mapper = new ObjectMapper();
		PositionDataRequest dataRequest = mapper.convertValue(request, PositionDataRequest.class);
		var hasAnyError_dataRequest = validator.validate(dataRequest);
		if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

		return dataRequest;
	}
}