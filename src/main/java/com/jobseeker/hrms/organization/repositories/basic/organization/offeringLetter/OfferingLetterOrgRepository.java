package com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter;

import org.jobseeker.organization.OfferingLetter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingLetterOrgRepository extends MongoRepository<OfferingLetter, String>, OfferingLetterOrgRepositoryExtend {
}
