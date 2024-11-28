package com.jobseeker.hrms.organization.repositories.basic.organization.importTemplate;

import org.bson.types.ObjectId;
import org.jobseeker.organization.CustomSource;
import org.jobseeker.organization.ImportTemplate;
import org.jobseeker.organization.sms.PkwtTemplateSMS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportTemplateOrgRepository extends MongoRepository<ImportTemplate, String> {
    @Query("{'company._id' : ?0, 'type': ?1}")
    Optional<ImportTemplate> findByCompanyAndType(String companyId, String type);
}
