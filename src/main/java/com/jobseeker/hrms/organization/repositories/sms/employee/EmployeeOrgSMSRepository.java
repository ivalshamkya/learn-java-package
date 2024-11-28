package com.jobseeker.hrms.organization.repositories.sms.employee;

import org.jobseeker.employee.lawson.LawsonEmployee;
import org.jobseeker.employee.sms.SmsEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOrgSMSRepository extends MongoRepository<SmsEmployee, String> {

    @Query(value = "{?0: ?1, status: 'ACTIVE' }", exists = true)
    boolean isExist(String query, Object id);
}
