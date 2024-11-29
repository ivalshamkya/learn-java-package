package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.education.Institution;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IInstitutionRepository extends MongoRepository<Institution, String> {
    @Aggregation(pipeline = {
            "{ $match: { 'name': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    Optional<Institution> findByName(String name);
}
