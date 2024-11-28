package com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill;

import org.jobseeker.organization.HardSkillOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HardSkillOrgOrgRepository extends MongoRepository<HardSkillOrganization, String>, HardSkillOrgOrgRepositoryExtend {
}
