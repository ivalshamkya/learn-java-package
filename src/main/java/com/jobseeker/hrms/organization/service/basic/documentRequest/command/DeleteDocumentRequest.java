package com.jobseeker.hrms.organization.service.basic.documentRequest.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.candidate.CandidateOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest.DocumentRequestOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.DocumentRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createDocumentRequest")
public class DeleteDocumentRequest {

    private final DocumentRequestOrgRepository documentRequestOrgRepository;
    private final CandidateOrgRepository candidateRepository;

    public String execute(String oid) {
        DocumentRequest documentRequest = documentRequestOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        documentRequest.setStatus(StatusData.DELETED);
        documentRequest.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Candidate">
        boolean existInCandidate = candidateRepository.isExist("documents.document._id", oid);
        if (existInCandidate) {
            throw new NoSuchElementException("Can't delete document request because it's used in candidate data.");
        }
        //</editor-fold>

        documentRequestOrgRepository.save(documentRequest);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
