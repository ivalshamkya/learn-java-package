package com.jobseeker.hrms.organization.service.basic.importTemplate.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.importTemplate.ImportTemplateDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IEmailTemplateMapper;
import com.jobseeker.hrms.organization.mapper.basic.IImportTemplateMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.emailTemplate.EmailTemplateOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.importTemplate.ImportTemplateOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.ImportTemplate;
import org.jobseeker.organization.JobLevel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("getImportTemplates")
public class GetImportTemplate {

    private final ImportTemplateOrgRepository repository;
    private final IImportTemplateMapper mapper;

    public ImportTemplateDataResponse execute(String type) {
        String companyId = ServletRequestAWS.getCompanyId();

            ImportTemplate data = repository.findByCompanyAndType(companyId, type)
                .orElseThrow(() -> new NoSuchElementException("No data found"));

        return mapper.toImportTemplateDataResponse(data);
    }
}
