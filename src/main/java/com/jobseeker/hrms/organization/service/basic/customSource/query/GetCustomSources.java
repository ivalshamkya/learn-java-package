package com.jobseeker.hrms.organization.service.basic.customSource.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.customSource.CustomSourceDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.mapper.basic.ICustomSourceMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.customSource.CustomSourceOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getCustomSources")
public class GetCustomSources {

    private final CustomSourceOrgRepository repository;
    private final ICustomSourceMapper mapper;

    public Page<CustomSourceDataResponse> execute(PaginationParams param) {
        return repository.findByDataTables(param)
                .map(mapper::toCustomSourceDataResponse);
    }
}
