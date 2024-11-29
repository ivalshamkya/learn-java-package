package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.education.Major;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IMajorRepository extends MongoRepository<Major, String> {
    @Aggregation(pipeline = {
            "{ $match: { 'name': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    public Optional<Major> findFirstByMajorName(String name);
}
