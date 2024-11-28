package com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest;

import org.jobseeker.organization.DocumentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRequestOrgRepository extends MongoRepository<DocumentRequest, String>, DocumentRequestOrgRepositoryExtend {
}
