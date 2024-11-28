package com.jobseeker.hrms.organization.repositories.sms.organization.branch;

import org.jobseeker.organization.sms.BranchSMS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchSMSRepository extends MongoRepository<BranchSMS, String>, BranchSMSRepositoryExtend {
}
