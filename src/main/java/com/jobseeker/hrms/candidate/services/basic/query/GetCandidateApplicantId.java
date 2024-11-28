package com.jobseeker.hrms.candidate.services.basic.query;

import com.jobseeker.hrms.candidate.data.basic.response.CandidateResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.candidate.repositories.CandidateRepository;
import org.jobseeker.vacancy.Applicant;
import org.jobseeker.vacancy.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getCandidateApplicantId")
public class GetCandidateApplicantId {

    private final ApplicantRepository applicantRepository;
    private final CandidateRepository candidateRepository;

    private final ICandidateMapper candidateMapper;

    public CandidateResponse execute(String oid) {
        Applicant applicant = applicantRepository.findById(oid)
                .orElseThrow(() -> new NoSuchElementException("Applicant not found"));
        Candidate candidate = candidateRepository.findById(applicant.getCandidate().get_id())
                .orElseThrow(() -> new NoSuchElementException("Candidate not found"));

        return candidateMapper.toResponse(candidate);
    }
}
