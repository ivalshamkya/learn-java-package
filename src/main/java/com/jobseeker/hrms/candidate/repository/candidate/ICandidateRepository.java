package com.jobseeker.hrms.candidate.repository.candidate;

import org.jobseeker.candidate.Candidate;
import org.jobseeker.config.repository.JSCRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidateRepository extends MongoRepository<Candidate, String>,
		JSCRepository<Candidate, String>,
		ICandidateRepositoryAdd<Candidate>
{

}
