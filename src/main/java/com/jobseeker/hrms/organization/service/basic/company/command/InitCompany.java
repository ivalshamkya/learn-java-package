package com.jobseeker.hrms.organization.service.basic.company.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataInitRequest;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("initCompany")
public class InitCompany {

    private final CompanyOrgRepository companyRepository;
    private final ICompanyMapper companyMapper;
    private final Validator validator;

    public CompanyDataResponse execute(CompanyDataInitRequest request) {
        Company newCompany = companyMapper.toInitialize(request);

        newCompany.setStatus(StatusData.ACTIVE);
        newCompany.setCreatedAt(LocalDateTime.now());
        return companyMapper.toCompanyDataResponse(companyRepository.save(newCompany));
    }

    private CompanyDataInitRequest getCompanyDataInitRequest(Map<Object, Object> request) {
        ObjectMapper mapper = new ObjectMapper();
        CompanyDataInitRequest dataRequest = mapper.convertValue(request, CompanyDataInitRequest.class);
        var hasAnyError_dataRequest = validator.validate(dataRequest);
        if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

        return dataRequest;
    }
}
