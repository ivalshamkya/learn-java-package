package com.jobseeker.hrms.organization.service.basic.documentRequest;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataRequest;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationBasicParams;
import com.jobseeker.hrms.organization.service.baseContract.BaseDocumentRequest;
import com.jobseeker.hrms.organization.service.basic.customGroupData.query.GetCustomGroupData;
import com.jobseeker.hrms.organization.service.basic.documentRequest.command.CreateDocumentRequest;
import com.jobseeker.hrms.organization.service.basic.documentRequest.command.DeleteDocumentRequest;
import com.jobseeker.hrms.organization.service.basic.documentRequest.command.UpdateDocumentRequest;
import com.jobseeker.hrms.organization.service.basic.documentRequest.query.GetDocumentRequest;
import com.jobseeker.hrms.organization.service.basic.documentRequest.query.GetDocumentRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@SuppressWarnings("unchecked")
public class DocumentRequestService<T> extends BaseDocumentRequest<T> {

    //<editor-fold desc="getDocumentRequest">
    @Autowired
    @Qualifier("getDocumentRequest")
    private GetDocumentRequest getDocumentRequest;

    @Override
    public T getDocumentRequest(String oid) {
        return (T) getDocumentRequest.execute(oid);
    }
    //</editor-fold>

    //<editor-fold desc="getDocumentRequests">
    @Autowired
    @Qualifier("getDocumentRequests")
    private GetDocumentRequests getDocumentRequests;

    @Override
    public Page<T> getDocumentRequests(Map<Object, Object> request) {
        PaginationBasicParams paginationParam = RequestHandler.remapToData(request, PaginationBasicParams.class);
        return (Page<T>) getDocumentRequests.execute(paginationParam);
    }
    //</editor-fold>

    //<editor-fold desc="createDocumentRequest">
    @Autowired
    @Qualifier("createDocumentRequest")
    private CreateDocumentRequest createDocumentRequest;

    @Override
    public T createDocumentRequest(Map<Object, Object> request) {
        DocumentRequestDataRequest objectRequest = RequestHandler.remapToData(request, DocumentRequestDataRequest.class);
        return (T) createDocumentRequest.execute(objectRequest);
    }
    //</editor-fold>

    //<editor-fold desc="updateDocumentRequest">
    @Autowired
    @Qualifier("updateDocumentRequest")
    private UpdateDocumentRequest updateDocumentRequest;

    @Override
    public T updateDocumentRequest(Map<Object, Object> request, String oid) {
        DocumentRequestDataRequest objectRequest = RequestHandler.remapToData(request, DocumentRequestDataRequest.class);
        return (T) updateDocumentRequest.execute(objectRequest, oid);
    }
    //</editor-fold>

    //<editor-fold desc="deleteDocumentRequest">
    @Autowired
    @Qualifier("deleteDocumentRequest")
    private DeleteDocumentRequest deleteDocumentRequest;

    @Override
    public String deleteDocumentRequest(String oid) {
        return deleteDocumentRequest.execute(oid);
    }
    //</editor-fold>
}