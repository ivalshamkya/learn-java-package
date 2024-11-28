package com.jobseeker.hrms.candidate.repository.candidate.explore;

import org.jobseeker.candidate.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExploreRepository extends MongoRepository<Candidate, String>, IExploreRepositoryExtend {
}
