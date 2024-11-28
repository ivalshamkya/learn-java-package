package com.jobseeker.hrms.organization.repositories.sms.vacancy;

import org.bson.types.ObjectId;
import org.jobseeker.vacancy.lawson.LawsonVacancy;
import org.jobseeker.vacancy.sms.SMSVacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyOrgSMSRepository extends MongoRepository<SMSVacancy, String>, VacancyOrgSMSRepositoryAdd {

    @Query(value = "{?0: ?1, status: 'ACTIVE' }", exists = true)
    boolean isExist(String query, Object id);
}
