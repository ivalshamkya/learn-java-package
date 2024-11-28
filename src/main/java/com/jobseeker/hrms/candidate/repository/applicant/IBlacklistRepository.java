package com.jobseeker.hrms.candidate.repository.applicant;

import org.jobseeker.vacancy.Blacklist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IBlacklistRepository extends MongoRepository<Blacklist, String> {
   @Query("{'company._id': ?0}")
   List<Blacklist> findAllByCompanyId(String companyId);
}
