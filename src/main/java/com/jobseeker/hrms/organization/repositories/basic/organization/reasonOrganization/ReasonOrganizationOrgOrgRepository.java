package com.jobseeker.hrms.organization.repositories.basic.organization.reasonOrganization;

import org.jobseeker.organization.ReasonOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReasonOrganizationOrgOrgRepository extends ReasonOrganizationOrgOrgRepositoryExtend, MongoRepository<ReasonOrganization, String> {
}
