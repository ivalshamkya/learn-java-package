package com.jobseeker.hrms.candidate.repository.candidate;

import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ICandidateRepositoryImpl implements ICandidateRepositoryAdd<Candidate> {

    private final BaseRepositoryImpl baseRepository;

    @Override
    public Page<Candidate> findCandidates(Map<Object, Object> request, List<String> blacklistedIds) {
        return baseRepository.findAllPaginated(request, blacklistedIds, Candidate.class);
    }

    @Override
    public List<Candidate> findCandidatesExcel(Map<Object, Object> request) {
        return baseRepository.findAll(request, Candidate.class);
    }
}
