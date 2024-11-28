package com.jobseeker.hrms.organization.repositories.basic.organization.vacancyTemplate.sms;

import org.jobseeker.organization.sms.VacancyTemplateSMS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyTemplateSMSRepository extends MongoRepository<VacancyTemplateSMS, String>, VacancyTemplateSMSRepositoryExtend {

}
