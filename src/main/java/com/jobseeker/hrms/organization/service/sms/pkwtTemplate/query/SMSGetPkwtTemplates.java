package com.jobseeker.hrms.organization.service.sms.pkwtTemplate.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.repositories.sms.organization.pkwtTemplate.PkwtTemplateOrgSmsRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.sms.PkwtTemplateSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("SMSGetPkwtTemplates")
public class SMSGetPkwtTemplates {

    private final PkwtTemplateOrgSmsRepository pkwtTemplateOrgSmsRepository;

    public List<PkwtTemplateSMS> execute() {
        String companyId = ServletRequestAWS.getCompanyId();
        return pkwtTemplateOrgSmsRepository.findByCompany(companyId);
    }
}
