package com.jobseeker.hrms.organization.repositories.basic.organization.religionOrganization;

import org.jobseeker.organization.ReligionOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReligionOrganizationOrgOrgRepository extends ReligionOrganizationOrgOrgRepositoryExtend, MongoRepository<ReligionOrganization, String> {
}
