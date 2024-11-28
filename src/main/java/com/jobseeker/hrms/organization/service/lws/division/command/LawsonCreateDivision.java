package com.jobseeker.hrms.organization.service.lws.division.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IDivisionLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.division.DivisionLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("LawsonCreateDivision")
public class LawsonCreateDivision {

    private final CompanyOrgRepository companyRepository;
    private final DivisionLWSRepository divisionLWSRepository;

    private final ICompanyMapper companyMapper;
    private final IDivisionLWSMapper divisionLWSMapper;

    public DivisionLWSDataResponse execute(DivisionLWSDataRequest request) throws Exception {
        DivisionLawson divisionLawson = composeBranch(request, null);

        return divisionLWSMapper.toDivisionLWSDataResponse(divisionLawson);
    }

    private DivisionLawson composeBranch(DivisionLWSDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        DivisionLawson divisionLawson = new DivisionLawson();
        if (oid != null) {
            divisionLawson = divisionLWSRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DIVISION_WITH_ID_FOUND.getMsg()));
            divisionLawson.setUpdatedAt(LocalDateTime.now());
        } else {
            divisionLawson.setStatus(StatusData.ACTIVE);
            divisionLawson.setCreatedAt(LocalDateTime.now());
        }
        divisionLawson.setCompany(companyMapper.toAttachCompany(company));

        divisionLWSMapper.toWriteDivisionLawson(divisionLawson, request);
        return divisionLWSRepository.save(divisionLawson);
    }
}
