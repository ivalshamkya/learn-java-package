package com.jobseeker.hrms.candidate.repository.organization;

import org.jobseeker.organization.HardSkillOrganization;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHardSkillOrgRepository extends MongoRepository<HardSkillOrganization, Integer> {
    @Aggregation(pipeline = {
            "{ $match: { 'name.id': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    HardSkillOrganization findByName(String name);
}
