package com.jobseeker.hrms.candidate.services.basic.query;

import com.jobseeker.hrms.candidate.data.basic.response.CandidateHistoryResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import com.jobseeker.hrms.candidate.repository.applicant.IApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.candidate.repositories.CandidateRepository;
import org.jobseeker.vacancy.Applicant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getCandidateHistory")
public class GetHistoryCandidate {

    private final CandidateRepository candidateRepository;
    private final IApplicantRepository applicantRepository;
    private final ICandidateMapper candidateMapper;

    public CandidateHistoryResponse execute(String oid) {
        List<Applicant> applicantList = applicantRepository.findApplicantByCandidateId(oid).orElse(new ArrayList<>());
        Candidate candidate = candidateRepository.findById(oid).orElseThrow(() -> new NoSuchElementException("Candidate not found"));
        CandidateHistoryResponse candidateHistoryResponse = new CandidateHistoryResponse();

        candidateHistoryResponse.setCandidate(candidateMapper.toCandidateSimpleData(candidate));

        for (Applicant applicant : applicantList) {
            applicant.getHistoryActivity().add(applicant.getCurrentActivity());
        }

        candidateHistoryResponse.setHistory(candidateMapper.toHistoriesData(applicantList));

        return candidateHistoryResponse;
    }

}
