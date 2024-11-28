package com.jobseeker.hrms.organization.repositories.basic.organization.workplacement;

import org.jobseeker.organization.Workplacement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkplacementOrgOrgRepository extends MongoRepository<Workplacement, String>, WorkplacementOrgOrgRepositoryExtend {
}
