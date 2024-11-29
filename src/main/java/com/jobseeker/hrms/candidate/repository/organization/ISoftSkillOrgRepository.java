package com.jobseeker.hrms.candidate.repository.organization;

import org.jobseeker.organization.SoftSkillOrganization;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoftSkillOrgRepository extends MongoRepository<SoftSkillOrganization, Integer> {
    @Aggregation(pipeline = {
            "{ $match: { 'name.id': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    SoftSkillOrganization findByName(String name);
}
