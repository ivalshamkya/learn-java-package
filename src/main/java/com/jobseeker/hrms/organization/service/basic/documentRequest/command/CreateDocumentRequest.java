package com.jobseeker.hrms.organization.service.basic.documentRequest.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataRequest;
import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IDocumentRequestMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest.DocumentRequestOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.DocumentRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createDocumentRequest")
public class CreateDocumentRequest {

    private final CompanyOrgRepository companyRepository;
    private final DocumentRequestOrgRepository documentRequestOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IDocumentRequestMapper documentRequestMapper;

    public DocumentRequestDataResponse execute(DocumentRequestDataRequest request) {
        DocumentRequest documentRequest = composeBranch(request, null);

        return documentRequestMapper.toDocumentRequestDataResponse(documentRequest);
    }

    private DocumentRequest composeBranch(DocumentRequestDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        DocumentRequest documentRequest = new DocumentRequest();
        if (oid != null) {
            documentRequest = documentRequestOrgRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
            documentRequest.setUpdatedAt(LocalDateTime.now());
        } else {
            documentRequest.setStatus(StatusData.ACTIVE);
            documentRequest.setCreatedAt(LocalDateTime.now());
        }

        documentRequest.setCompany(companyMapper.toAttachCompany(company));

        documentRequestMapper.toWriteDocumentRequest(documentRequest, request);
        return documentRequestOrgRepository.save(documentRequest);
    }
}
