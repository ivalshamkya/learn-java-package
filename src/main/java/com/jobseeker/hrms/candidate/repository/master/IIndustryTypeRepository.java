package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.IndustryType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIndustryTypeRepository extends MongoRepository<IndustryType, String> {
    @Aggregation(pipeline = {
            "{ $match: { 'name.id': { $regex: ?0, $options: 'i' } } }",
            "{ $limit: 1 }"
    })
    IndustryType findByName(String name);
}
