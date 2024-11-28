package com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage;

import org.jobseeker.organization.RecruitmentStage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentStageOrgRepository extends MongoRepository<RecruitmentStage, String>, RecruitmentStageOrgRepositoryExtend {
}
