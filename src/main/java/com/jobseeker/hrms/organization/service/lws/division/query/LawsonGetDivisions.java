package com.jobseeker.hrms.organization.service.lws.division.query;

import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IDivisionLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.division.DivisionLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("LawsonGetDivisions")
public class LawsonGetDivisions {

    private final DivisionLWSRepository divisionLWSRepository;

    private final IDivisionLWSMapper divisionLWSMapper;

    public Page<DivisionLWSDataResponse> execute(PaginationParam param) {
        return divisionLWSRepository.findByDataTables(param)
                .map(divisionLWSMapper::toDivisionLWSDataResponse);
    }
}
