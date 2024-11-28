package com.jobseeker.hrms.organization.service.sms.vacancyTemplate.query;

import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataPagingResponse;
import com.jobseeker.hrms.organization.mapper.basic.IVacancyTemplateMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.vacancyTemplate.sms.VacancyTemplateSMSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getJobTemplates")
public class SMSGetJobTemplates {

    private final VacancyTemplateSMSRepository vacancyTemplateSMSRepository;

    private final IVacancyTemplateMapper vacancyTemplateMapper;

    public Page<JobTemplateDataPagingResponse> execute(PaginationParam param) {
        return vacancyTemplateSMSRepository.findByDataTables(param)
                .map(vacancyTemplateMapper::toJobTemplatePagingResponse);
    }
}
