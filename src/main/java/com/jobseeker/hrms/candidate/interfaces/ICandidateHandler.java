package com.jobseeker.hrms.candidate.interfaces;

import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Map;

public interface ICandidateHandler<T> {

    T createCandidate(Map<Object, Object> request);

    Page<T> getCandidates(Map<Object, Object> request);

    T getCandidate(String oid);

    // Get by Applicant Id
    T getCandidateByApplicantId(String oid);

    T getCandidateProfileById(String oid);

    void deleteCandidate(String oid) throws Exception;

    T rejectOrApproveCandidate(Map<Object, Object> request) throws Exception;

    Object getCandidateHistory(String oid);

    Object exportToExcel(Map<Object, Object> request) throws Exception;

    void sendReminderCompleteProfile(String candidateId);

    void importCsv(Map<Object, Object> request) throws IOException;
}
