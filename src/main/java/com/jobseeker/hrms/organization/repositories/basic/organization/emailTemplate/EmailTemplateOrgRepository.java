package com.jobseeker.hrms.organization.repositories.basic.organization.emailTemplate;

import org.jobseeker.organization.CustomSource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateOrgRepository extends MongoRepository<CustomSource, String>, EmailTemplateOrgRepositoryExtend {
}
