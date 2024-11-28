package com.jobseeker.hrms.organization.repositories.lws.candidate;

import org.jobseeker.candidate.lawson.LawsonCandidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateOrgLWSRepository extends MongoRepository<LawsonCandidate, String> {
}
