package com.jobseeker.hrms.organization.service.basic.workplacement.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IWorkplacementMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.workplacement.WorkplacementOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Workplacement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getWorkplacement")
public class GetWorkplacement {

	private final WorkplacementOrgOrgRepository workplacementOrgRepository;
	private final IWorkplacementMapper workplacementMapper;

	public Page<WorkplacementDataResponse> executeMany(Map<Object, Object> param) {
		PaginationParam paginationParam = RequestHandler.remapToData(param, PaginationParam.class);
		return workplacementOrgRepository.findByDataTables(paginationParam)
				.map(workplacementMapper::toWorkplacementDataResponse);
	}

	public WorkplacementDataResponse execute(String oid) {
		Workplacement workplacement = workplacementOrgRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_RECRUITMENT_STAGE_WITH_ID_FOUND.getMsg()));
		return workplacementMapper.toWorkplacementDataResponse(workplacement);
	}
}
