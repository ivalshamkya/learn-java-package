package com.jobseeker.hrms.organization.service.basic.reasonOrganization.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.reasonOrganization.ReasonOrganizationDataRequest;
import com.jobseeker.hrms.organization.data.basic.reasonOrganization.ReasonOrganizationDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IReasonOrganizationMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.reasonOrganization.ReasonOrganizationOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getReasonsService")
public class GetReasonsService {
    private final ReasonOrganizationOrgOrgRepository reasonOrganizationRepository;
    private final IReasonOrganizationMapper reasonOrganizationMapper;
    
    public Page<ReasonOrganizationDataResponse> execute(Map<Object, Object> param) {
        ReasonOrganizationDataRequest dataParam = RequestHandler.remapToData(param, ReasonOrganizationDataRequest.class);
        return reasonOrganizationRepository.findDataTables(dataParam)
            .map(reasonOrganizationMapper::toReasonDataResponse);
    }
}
