package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenderRepository extends MongoRepository<Gender, String> {
   @Query("{type:?0}")
    Gender findByType(String name);
}
