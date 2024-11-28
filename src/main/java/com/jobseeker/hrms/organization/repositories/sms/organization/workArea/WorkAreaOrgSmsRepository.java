package com.jobseeker.hrms.organization.repositories.sms.organization.workArea;

import org.jobseeker.organization.sms.WorkAreaSMS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkAreaOrgSmsRepository extends MongoRepository<WorkAreaSMS, String>, WorkAreaOrgSmsRepositoryExtend {
}
