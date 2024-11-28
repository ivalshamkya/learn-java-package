package com.jobseeker.hrms.organization.service.basic.workplacement.query;

import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataResponse;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.mapper.basic.IWorkplacementMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.workplacement.WorkplacementOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getWorkplacements")
public class GetWorkplacements {

	private final WorkplacementOrgOrgRepository repository;
	private final IWorkplacementMapper mapper;

	public Page<WorkplacementDataResponse> execute(PaginationLWSParams params) {
		return repository.findByDataTables(params)
				.map(mapper::toWorkplacementDataResponse);
	}
}
