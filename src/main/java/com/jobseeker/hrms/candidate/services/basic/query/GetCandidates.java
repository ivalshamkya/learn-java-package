package com.jobseeker.hrms.candidate.services.basic.query;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.data.basic.response.CandidatePagingResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import com.jobseeker.hrms.candidate.repository.applicant.IBlacklistRepository;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.vacancy.Blacklist;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getCandidates")
public class GetCandidates {

    private final ICandidateRepository candidateRepository;
    private final IBlacklistRepository blacklistRepository;

    private final ICandidateMapper candidateMapper;

    public Page<CandidatePagingResponse> execute(Map<Object, Object> request) {
        List<Blacklist> blacklists = blacklistRepository.findAllByCompanyId(ServletRequestAWS.getCompanyId());

        List<String> blacklistedCandidateIds = new ArrayList<>();
        if (!blacklists.isEmpty()) {
            for (Blacklist data : blacklists) {
                blacklistedCandidateIds.add(data.getCandidate().get_id());
            }
        }

        Page<Candidate> paginated = candidateRepository.findCandidates(request, blacklistedCandidateIds);
        return paginated.map(candidateMapper::toPagingResponse);
    }
}
