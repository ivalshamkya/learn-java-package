package com.jobseeker.hrms.candidate.services.basic.query;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.data.basic.response.CandidateResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetCandidateProfile {

    private final ICandidateRepository candidateRepository;

    private final ICandidateMapper candidateMapper;

    /*
     * For HRMS
     * */
    public CandidateResponse execute(String id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Candidate not found"));
        return candidateMapper.toResponse(candidate);
    }

    /*
    * For Careersite
    * */
    public CandidateResponse execute() {
        Candidate candidate = candidateRepository.findById(ServletRequestAWS.getCandidateId())
                .orElseThrow(() -> new NoSuchElementException("Candidate not found"));
        return candidateMapper.toResponse(candidate);
    }
}
