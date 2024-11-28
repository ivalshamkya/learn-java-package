package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.SIMLicense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISIMRepository extends MongoRepository<SIMLicense, String> {
    @Query("{name: ?0}")
    SIMLicense findByName(String type);
}
