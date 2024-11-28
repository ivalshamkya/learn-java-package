package com.jobseeker.hrms.organization.service.basic.documentRequest.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationBasicParams;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IDocumentRequestMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest.DocumentRequestOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.DocumentRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getDocumentRequest")
public class GetDocumentRequest {

    private final DocumentRequestOrgRepository documentRequestOrgRepository;
    private final IDocumentRequestMapper documentRequestMapper;

    public DocumentRequestDataResponse execute(String oid) {
        DocumentRequest documentRequest = documentRequestOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        return documentRequestMapper.toDocumentRequestDataResponse(documentRequest);
    }
}
