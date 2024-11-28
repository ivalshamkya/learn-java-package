package com.jobseeker.hrms.organization.service.basic.documentRequest.query;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationBasicParams;
import com.jobseeker.hrms.organization.mapper.basic.IDocumentRequestMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest.DocumentRequestOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getDocumentRequests")
public class GetDocumentRequests {

    private final DocumentRequestOrgRepository documentRequestOrgRepository;
    private final IDocumentRequestMapper documentRequestMapper;

    public Page<DocumentRequestDataResponse> execute(PaginationBasicParams param) {
        return documentRequestOrgRepository.findByDataTables(param)
                .map(documentRequestMapper::toDocumentRequestDataResponse);
    }
}
