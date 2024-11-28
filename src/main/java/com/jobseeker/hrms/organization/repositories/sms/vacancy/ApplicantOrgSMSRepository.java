package com.jobseeker.hrms.organization.repositories.sms.vacancy;

import org.jobseeker.vacancy.Applicant;
import org.jobseeker.vacancy.sms.SMSVacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantOrgSMSRepository extends MongoRepository<Applicant, String> {

    @Query(value = "{?0: ?1, status: 'ACTIVE' }", exists = true)
    boolean isExist(String query, Object id);

    @Query(value = "{'$or': [{'current_activity.offering_letter.pkwt_template._id': ?0}, {'history_activity.offering_letter.pkwt_template._id': ?0}], status: 'ACTIVE' }", exists = true)
    boolean existsByPwktTemplateId(String id);
}
