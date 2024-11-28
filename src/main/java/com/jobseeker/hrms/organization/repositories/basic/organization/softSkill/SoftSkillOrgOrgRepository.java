package com.jobseeker.hrms.organization.repositories.basic.organization.softSkill;

import org.jobseeker.organization.SoftSkillOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SoftSkillOrgOrgRepository extends MongoRepository<SoftSkillOrganization, String>, SoftSkillOrgOrgRepositoryExtend {
}
