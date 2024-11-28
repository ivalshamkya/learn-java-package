package com.jobseeker.hrms.organization.service.sms.vacancyTemplate.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataRequest;
import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IVacancyTemplateMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.vacancyTemplate.sms.VacancyTemplateSMSRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.sms.VacancyTemplateSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createJobTemplate")
public class SMSCreateJobTemplate {

    private final CompanyOrgRepository companyRepository;
    private final VacancyTemplateSMSRepository vacancyTemplateSMSRepository;

    private final IVacancyTemplateMapper vacancyTemplateMapper;
    private final ICompanyMapper companyMapper;

    public JobTemplateDataResponse execute(JobTemplateDataRequest request) {
        VacancyTemplateSMS vacancyTemplateSMS = composeJobTemplate(request, null);

        return vacancyTemplateMapper.toJobTemplateResponse(vacancyTemplateSMS);
    }

    private VacancyTemplateSMS composeJobTemplate(JobTemplateDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        VacancyTemplateSMS vacancyTemplateSMS = new VacancyTemplateSMS();
        if (oid != null) {
            vacancyTemplateSMS = vacancyTemplateSMSRepository.findById(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_VACANCY_TEMPLATE_WITH_ID_FOUND.getMsg()));
            vacancyTemplateSMS.setUpdatedAt(LocalDateTime.now());
        } else {
            vacancyTemplateSMS.setStatus(StatusData.ACTIVE);
            vacancyTemplateSMS.setCreatedAt(LocalDateTime.now());
        }
        vacancyTemplateSMS.setCompany(companyMapper.toAttachCompany(company));

        vacancyTemplateMapper.toWriteVacancyTemplateSMS(vacancyTemplateSMS, request);
        return vacancyTemplateSMSRepository.save(vacancyTemplateSMS);
    }
}
