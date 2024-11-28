package com.jobseeker.hrms.candidate.services.basic.command;

import com.jobseeker.hrms.candidate.repository.applicant.IApplicantRepository;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCandidate {

    private final ICandidateRepository candidateRepository;
    private final IApplicantRepository applicantRepository;

    public void execute(String oid) {
        candidateRepository.deleteById(oid);
        applicantRepository.deleteAllByCandidate__id(oid);
    }
}
