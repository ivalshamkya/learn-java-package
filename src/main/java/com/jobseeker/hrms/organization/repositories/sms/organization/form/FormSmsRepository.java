package com.jobseeker.hrms.organization.repositories.sms.organization.form;


import org.jobseeker.organization.sms.FormSMS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormSmsRepository extends MongoRepository<FormSMS, String>, FormSmsRepositoryExtend {

}
