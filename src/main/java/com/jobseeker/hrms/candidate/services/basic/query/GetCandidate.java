package com.jobseeker.hrms.candidate.services.basic.query;

import com.jobseeker.hrms.candidate.data.basic.response.CandidateResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getCandidate")
public class GetCandidate {

    private final ICandidateRepository candidateRepository;

    private final ICandidateMapper candidateMapper;

    public CandidateResponse execute(String oid) {
        Candidate candidate = candidateRepository
                .findById(oid)
                .orElseThrow(() -> new NoSuchElementException("Candidate not found"));
        return candidateMapper.toResponse(candidate);
    }
}
