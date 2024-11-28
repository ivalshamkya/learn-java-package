package com.jobseeker.hrms.candidate.services.basic;

import com.jobseeker.hrms.candidate.services.BaseCandidateService;
import com.jobseeker.hrms.candidate.services.basic.command.*;
import com.jobseeker.hrms.candidate.services.basic.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class CandidateService<T> extends BaseCandidateService<T> {

    //<editor-fold desc="getCandidates">
    @Autowired
    @Qualifier("getCandidates")
    private GetCandidates getCandidates;

    @Override
    public Page<T> getCandidates(Map<Object, Object> request) {
        return (Page<T>) getCandidates.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="createCandidate">
    @Autowired
    @Qualifier("createCandidate")
    private CreateCandidate createCandidate;

    @Override
    public T createCandidate(Map<Object, Object> request) {
        return (T) createCandidate.executeFromUploadResume(request);
    }
    //</editor-fold>

    //<editor-fold desc="getCandidate">
    @Autowired
    @Qualifier("getCandidate")
    private GetCandidate getCandidate;

    @Override
    public T getCandidate(String request) {
        return (T) getCandidate.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="getCandidateByApplicantId">
    @Autowired
    @Qualifier("getCandidateApplicantId")
    private GetCandidateApplicantId getCandidateByApplicantId;

    @Override
    public T getCandidateByApplicantId(String request) {
        return (T) getCandidateByApplicantId.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="getCandidateProfile">
    @Autowired
    @Qualifier("getCandidateProfile")
    private GetCandidateProfile getCandidateProfile;

    @Override
    public T getCandidateProfileById(String request) {
        return (T) getCandidateProfile.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="getCandidateHistory">
    @Autowired
    @Qualifier("getCandidateHistory")
    private GetHistoryCandidate getCandidateHistory;

    @Override
    public T getCandidateHistory(String request) {
        return (T) getCandidateHistory.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="deleteCandidate">
    @Autowired
    @Qualifier("deleteCandidate")
    private DeleteCandidate deleteCandidate;

    @Override
    public void deleteCandidate(String request) {
        deleteCandidate.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="exportToExcel">
    @Autowired
    @Qualifier("exportToExcel")
    private ExportToExcel exportToExcel;

    @Override
    public T exportToExcel(Map<Object, Object> request) throws IOException {
        return (T) exportToExcel.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="importCsv">
    @Autowired
    @Qualifier("importCsv")
    private ImportCsv importCsv;

    @Override
    public void importCsv(Map<Object, Object> request) throws IOException {
        importCsv.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="sendReminderCompleteProfile">
    @Autowired
    @Qualifier("sendReminderCompleteProfile")
    private SendReminderCompleteProfile sendReminderCompleteProfile;

    @Override
    public void sendReminderCompleteProfile(String candidateId) {
        sendReminderCompleteProfile.execute(candidateId);
    }
    //</editor-fold>
}
