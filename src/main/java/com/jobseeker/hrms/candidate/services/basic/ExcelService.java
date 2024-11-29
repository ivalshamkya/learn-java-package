package com.jobseeker.hrms.candidate.services.basic;

import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final ICandidateRepository candidateRepository;

}
