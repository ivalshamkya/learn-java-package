package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.education.EducationLevel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IEducationLevelRepository extends MongoRepository<EducationLevel, Integer> {
    @Aggregation(pipeline = {
            "{ $match: { 'name.id': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    EducationLevel findByName(String name);
}
