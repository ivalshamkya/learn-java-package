package com.jobseeker.hrms.organization.service.lws.businessUnit.query;

import com.jobseeker.hrms.organization.data.lws.businessUnit.BusinessUnitLWSDataResponse;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.mapper.lws.IBusinessUnitLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.businessUnit.BusinessUnitOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetBusinessUnitsService")
public class LawsonGetBusinessUnitsService {

	private final BusinessUnitOrgLWSRepository repository;
	private final IBusinessUnitLWSMapper mapper;

	public Page<BusinessUnitLWSDataResponse> execute(PaginationLWSParams params) {
		return repository.findByDataTables(params)
				.map(mapper::toBusinessUnitLWSDataResponse);
	}
}
