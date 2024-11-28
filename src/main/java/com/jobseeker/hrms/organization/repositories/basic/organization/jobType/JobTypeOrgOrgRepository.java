package com.jobseeker.hrms.organization.repositories.basic.organization.jobType;

import org.jobseeker.organization.JobType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeOrgOrgRepository extends MongoRepository<JobType, String>, JobTypeOrgOrgRepositoryExtend {
}
