package com.jobseeker.hrms.organization.repositories.sms.organization.importLog;

import org.jobseeker.logs.ImportLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportLogSMSRepository extends MongoRepository<ImportLog, String>, ImportLogSMSRepositoryExtend {
}
