package com.jobseeker.hrms.organization.service.lws.satNationality.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.nationality.NationalityDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.INationalityMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.nationality.NationalityOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetSATNationalities")
public class LawsonGetSATNationalities {
    
    private final NationalityOrgRepository lawSatNationalityOrgLWSRepository;
    
    private final INationalityMapper nationalityLWSMapper;
    
    public Page<NationalityDataResponse> execute(Map<Object, Object> request) {
        PaginationParam params = RequestHandler.remapToData(request, PaginationParam.class);
        return lawSatNationalityOrgLWSRepository.findByDataTables(params)
            .map(nationalityLWSMapper::toLawsonSATNationality);
    }
}
