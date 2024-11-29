package com.jobseeker.hrms.candidate.repository.candidate.explore;

import com.jobseeker.hrms.candidate.data.basic.request.explore.ExploreDataRequest;
import org.jobseeker.candidate.Candidate;
import org.springframework.data.domain.Page;

public interface IExploreRepositoryExtend {

    Page<Candidate> findCandidates(ExploreDataRequest exploreDataRequest);
}
