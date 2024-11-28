package com.jobseeker.hrms.organization.repositories.basic.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryExtend{

	@Autowired
	@Qualifier("employeeMongoTemplate")
	private MongoTemplate mongoTemplate;

}
