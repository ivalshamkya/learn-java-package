package com.jobseeker.hrms.organization.service.lws.workplacement.query;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.workplacement.WorkplacementLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IWorkplacementLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.workplacement.WorkplacementOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetWorkplacementsService")
public class LawsonGetWorkplacementsService {

	private final WorkplacementOrgLWSRepository repository;
	private final IWorkplacementLWSMapper mapper;

	public Page<WorkplacementLWSDataResponse> execute(PaginationLWSParams params) {
		return repository.findByDataTables(params)
				.map(mapper::toWorkplacementDataResponse);
	}
}
