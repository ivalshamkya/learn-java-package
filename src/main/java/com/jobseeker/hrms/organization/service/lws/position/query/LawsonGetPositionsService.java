package com.jobseeker.hrms.organization.service.lws.position.query;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.position.PositionLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IPositionLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.position.PositionOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetPositionsService")
public class LawsonGetPositionsService {

	private final PositionOrgLWSRepository repository;
	private final IPositionLWSMapper mapper;

	public Page<PositionLWSDataResponse> execute(PaginationLWSParams params) {
		return repository.findByDataTables(params)
				.map(mapper::toPositionDataResponse);
	}
}
