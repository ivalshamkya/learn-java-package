package com.jobseeker.hrms.candidate.services;

import com.jobseeker.hrms.candidate.interfaces.ICandidateHandler;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class BaseCandidateService<T> implements ICandidateHandler<T> {

    @Override
    public Page<T> getCandidates(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T createCandidate(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T getCandidate(String oid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T getCandidateByApplicantId(String oid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T getCandidateProfileById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteCandidate(String oid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T rejectOrApproveCandidate(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T getCandidateHistory(String oid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T exportToExcel(Map<Object, Object> request) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendReminderCompleteProfile(String candidateId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void importCsv(Map<Object, Object> request) throws IOException {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
