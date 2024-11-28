package com.jobseeker.hrms.organization.repositories.basic.organization.customSource;

import org.jobseeker.organization.CustomSource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSourceOrgRepository extends MongoRepository<CustomSource, String>, CustomSourceOrgRepositoryExtend {
}
