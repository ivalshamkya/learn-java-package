package com.jobseeker.hrms.candidate.repository.log;

import org.jobseeker.config.repository.JSCRepository;
import org.jobseeker.logs.ImportLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImportLogRepository extends MongoRepository<ImportLog, String>, JSCRepository<ImportLog, String> {
}
