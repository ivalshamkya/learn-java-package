package com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel;

import org.jobseeker.organization.JobLevel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLevelOrgOrgRepository extends MongoRepository<JobLevel, String>, JobLevelOrgOrgRepositoryExtend {
}
