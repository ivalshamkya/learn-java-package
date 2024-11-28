package com.jobseeker.hrms.organization.service.basic.emailTemplate.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.emailTemplate.EmailTemplateDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.mapper.basic.IEmailTemplateMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.emailTemplate.EmailTemplateOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getEmailTemplates")
public class GetEmailTemplates {

    private final EmailTemplateOrgRepository repository;
    private final IEmailTemplateMapper mapper;

    public Page<EmailTemplateDataResponse> execute(Map<Object, Object> generalParam) {
        PaginationParams param = RequestHandler.remapToData(generalParam, PaginationParams.class);

        return repository.findByDataTables(param)
                .map(mapper::toEmailTemplateDataResponse);
    }
}
