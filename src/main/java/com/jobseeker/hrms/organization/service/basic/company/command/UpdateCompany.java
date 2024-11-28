package com.jobseeker.hrms.organization.service.basic.company.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataResponse;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataSimpleRequest;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("updateCompany")
public class UpdateCompany {

    private final CompanyOrgRepository companyRepository;
    private final ICompanyMapper companyMapper;
    private final Validator validator;

    public CompanyDataResponse execute(CompanyDataSimpleRequest request) {
        String companyId = ServletRequestAWS.getCompanyId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        company.setUpdatedAt(LocalDateTime.now());
        companyMapper.updateFromSimpleRequest(company, request);

        companyRepository.update(company, companyId);
        return companyMapper.toCompanyDataResponse(company);
    }

    private CompanyDataSimpleRequest getCompanyDataSimpleRequest(Map<Object, Object> request) {
        ObjectMapper mapper = new ObjectMapper();
        CompanyDataSimpleRequest dataRequest = mapper.convertValue(request, CompanyDataSimpleRequest.class);
        var hasAnyError_dataRequest = validator.validate(dataRequest);
        if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

        return dataRequest;
    }
}
