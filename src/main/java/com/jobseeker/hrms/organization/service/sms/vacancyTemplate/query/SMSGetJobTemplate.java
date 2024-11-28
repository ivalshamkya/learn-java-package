package com.jobseeker.hrms.organization.service.sms.vacancyTemplate.query;

import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IVacancyTemplateMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.vacancyTemplate.sms.VacancyTemplateSMSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.sms.VacancyTemplateSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getJobTemplate")
public class SMSGetJobTemplate {

    private final VacancyTemplateSMSRepository vacancyTemplateSMSRepository;

    private final IVacancyTemplateMapper vacancyTemplateMapper;

    public JobTemplateDataResponse execute(String oid) {
        VacancyTemplateSMS vacancyTemplateSMS = vacancyTemplateSMSRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_VACANCY_TEMPLATE_WITH_ID_FOUND.getMsg()));
        return vacancyTemplateMapper.toJobTemplateResponse(vacancyTemplateSMS);
    }
}
