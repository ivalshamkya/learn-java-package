package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.JobFunction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobFunctionsRepository extends MongoRepository<JobFunction, String>, IJobFunctionsRepositoryExtend {

}
