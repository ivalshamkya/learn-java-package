package com.jobseeker.hrms.organization.service.lws.satDisability.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.disability.DisabilityDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IDisabilityMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.disability.DisabilityOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetSATDisabilities")
public class LawsonGetSATDisabilities {
    private final DisabilityOrgOrgRepository satDisabilityOrgLWSRepository;
    private final IDisabilityMapper satDisabilityLWSMapper;
    
    public Page<DisabilityDataResponse> execute(Map<Object, Object> params) {
        PaginationParam dataParam = RequestHandler.remapToData(params, PaginationParam.class);
        return satDisabilityOrgLWSRepository.findByDataTables(dataParam)
            .map(satDisabilityLWSMapper::toLawsonSATDisability);
    }
    
}
