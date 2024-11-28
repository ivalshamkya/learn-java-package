package com.jobseeker.hrms.organization.service.basic.religionOrganization.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.religion.ReligionDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IReligionOrganizationMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.religionOrganization.ReligionOrganizationOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier("getReligionsService")
public class GetReligionsService {
    private final ReligionOrganizationOrgOrgRepository religionOrganizationRepository;
    private final IReligionOrganizationMapper religionOrganizationMapper;
    
    public Page<ReligionDataResponse> execute(Map<Object,Object> param) {
        PaginationParam dataParam = RequestHandler.remapToData(param, PaginationParam.class);
        return religionOrganizationRepository.findDataTables(dataParam)
            .map(religionOrganizationMapper::toReligionDataResponse);
    }
}
