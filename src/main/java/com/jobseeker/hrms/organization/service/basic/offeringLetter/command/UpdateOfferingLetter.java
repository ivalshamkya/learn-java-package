package com.jobseeker.hrms.organization.service.basic.offeringLetter.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataRequest;
import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IOfferingLetterMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter.OfferingLetterOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.OfferingLetter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("updateOfferingLetter")
public class UpdateOfferingLetter {

    private final CompanyOrgRepository companyRepository;
    private final OfferingLetterOrgRepository offeringLetterOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IOfferingLetterMapper offeringLetterMapper;

    public OfferingLetterDataResponse execute(OfferingLetterDataRequest request) {
        OfferingLetter offeringLetter = composeBranch(request);

        return offeringLetterMapper.toOfferingLetterDataResponse(offeringLetter);
    }

    private OfferingLetter composeBranch(OfferingLetterDataRequest request) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        OfferingLetter offeringLetter = offeringLetterOrgRepository.findFirstByActive()
                .orElse(new OfferingLetter());
        if (offeringLetter.get_id() != null) {
            offeringLetter.setUpdatedAt(LocalDateTime.now());
        } else {
            offeringLetter.setStatus(StatusData.ACTIVE);
            offeringLetter.setCreatedAt(LocalDateTime.now());
        }

        offeringLetter.setCompany(companyMapper.toAttachCompany(company));

        offeringLetterMapper.toWriteOfferingLetter(offeringLetter, request);
        return offeringLetterOrgRepository.save(offeringLetter);
    }
}
