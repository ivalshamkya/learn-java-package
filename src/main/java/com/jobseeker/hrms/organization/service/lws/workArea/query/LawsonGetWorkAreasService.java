package com.jobseeker.hrms.organization.service.lws.workArea.query;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IWorkAreaLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.workArea.WorkAreaOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.workArea.WorkAreaOrgLWSRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetWorkAreasService")
public class LawsonGetWorkAreasService {

	private final WorkAreaOrgLWSRepositoryImpl repository;
	private final IWorkAreaLWSMapper departmentMapper;

	public Page<WorkAreaLWSDataResponse> execute(PaginationLWSParams params) {
		return repository.findByDataTables(params)
				.map(departmentMapper::toWorkAreaLWSDataResponse);
	}
}
