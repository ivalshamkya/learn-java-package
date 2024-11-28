package com.jobseeker.hrms.organization.repositories.sms.organization.pkwtTemplate;

import org.bson.types.ObjectId;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.jobseeker.organization.sms.PkwtTemplateSMS;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PkwtTemplateOrgSmsRepository extends HelperOrganizationRepository<PkwtTemplateSMS> {
    @Query("{'company._id' : ?0, 'deleted_at': {'$exists':  false}}")
    List<PkwtTemplateSMS> findByCompany2(ObjectId var1);
}
