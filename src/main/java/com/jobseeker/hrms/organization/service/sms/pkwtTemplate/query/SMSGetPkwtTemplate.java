package com.jobseeker.hrms.organization.service.sms.pkwtTemplate.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.sms.organization.pkwtTemplate.PkwtTemplateOrgSmsRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.sms.PkwtTemplateSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("SMSGetPkwtTemplate")
public class SMSGetPkwtTemplate {

    private final PkwtTemplateOrgSmsRepository pkwtTemplateOrgSmsRepository;

    public PkwtTemplateSMS execute(String id) {
        String companyId = ServletRequestAWS.getCompanyId();
        PkwtTemplateSMS data = pkwtTemplateOrgSmsRepository.findByCompanyAndId(companyId, id)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_SOURCE_APP_FOUND.getMsg()));
        return data;
    }
}
