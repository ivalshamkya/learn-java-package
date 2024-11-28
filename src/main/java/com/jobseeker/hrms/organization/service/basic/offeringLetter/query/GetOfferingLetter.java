package com.jobseeker.hrms.organization.service.basic.offeringLetter.query;

import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IOfferingLetterMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter.OfferingLetterOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.OfferingLetter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getOfferingLetter")
public class GetOfferingLetter {

    private final OfferingLetterOrgRepository offeringLetterOrgRepository;

    private final IOfferingLetterMapper offeringLetterMapper;

    public OfferingLetterDataResponse execute() {
        OfferingLetter offeringLetter = offeringLetterOrgRepository.findFirstByActive()
                .orElse(new OfferingLetter());
        return offeringLetterMapper.toOfferingLetterDataResponse(offeringLetter);
    }
}
