package com.jobseeker.hrms.organization.repositories.basic.organization.company;

import org.jobseeker.config.repository.JSCRepository;
import org.jobseeker.organization.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyOrgRepository extends MongoRepository<Company, String>,
		JSCRepository<Company, String>
{
}
