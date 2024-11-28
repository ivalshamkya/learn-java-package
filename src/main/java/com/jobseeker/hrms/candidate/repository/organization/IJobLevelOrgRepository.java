package com.jobseeker.hrms.candidate.repository.organization;

import org.jobseeker.organization.JobLevel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobLevelOrgRepository extends MongoRepository<JobLevel, Integer> {
    @Aggregation(pipeline = {
            "{ $match: { 'name': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    JobLevel findByName(String name);
}
