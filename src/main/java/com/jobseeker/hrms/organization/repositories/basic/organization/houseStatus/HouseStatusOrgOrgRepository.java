package com.jobseeker.hrms.organization.repositories.basic.organization.houseStatus;

import com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill.HardSkillOrgOrgRepositoryExtend;
import org.jobseeker.organization.HouseStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HouseStatusOrgOrgRepository extends MongoRepository<HouseStatus, String>, HouseStatusOrgOrgRepositoryExtend {
}
