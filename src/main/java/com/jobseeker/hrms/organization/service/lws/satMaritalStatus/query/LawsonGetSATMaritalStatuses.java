package com.jobseeker.hrms.organization.service.lws.satMaritalStatus.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.maritalStatus.MaritalStatusDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IMaritalStatusMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.maritalStatus.MaritalStatusOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetSATMaritalStatuses")
public class LawsonGetSATMaritalStatuses {
    private final MaritalStatusOrgOrgRepository satMaritalStatusOrgLWSRepository;
    
    private final IMaritalStatusMapper maritalStatusLWSMapper;
    
    public Page<MaritalStatusDataResponse> execute(Map<Object, Object> params) {
        PaginationParam param = RequestHandler.remapToData(params, PaginationParam.class);
        return satMaritalStatusOrgLWSRepository.findByDataTables(param)
            .map(maritalStatusLWSMapper::toLawsonSATMaritalStatus);
    }
}
