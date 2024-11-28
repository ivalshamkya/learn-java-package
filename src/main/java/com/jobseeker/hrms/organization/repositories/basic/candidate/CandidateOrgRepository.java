package com.jobseeker.hrms.organization.repositories.basic.candidate;

import org.jobseeker.candidate.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateOrgRepository extends MongoRepository<Candidate, String> {

    @Query(value = "{?0: ?1}", exists = true)
    boolean isExist(String query, Object id);
}
