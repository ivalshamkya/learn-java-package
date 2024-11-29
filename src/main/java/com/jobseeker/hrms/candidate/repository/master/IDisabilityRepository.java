package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.Disability;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisabilityRepository extends MongoRepository<Disability, String> {
    @Aggregation(pipeline = {
            "{ $match: { 'name.id': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    Disability findByDisabilityName(String name);
}
