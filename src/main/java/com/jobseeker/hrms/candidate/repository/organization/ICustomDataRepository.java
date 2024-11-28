package com.jobseeker.hrms.candidate.repository.organization;

import org.jobseeker.organization.CustomData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomDataRepository extends MongoRepository<CustomData, String> {
    @Query("{name: RegExp(?0, 'i'), 'custom_group.code': 'mobility'}")
    CustomData findByName(String name);
}
