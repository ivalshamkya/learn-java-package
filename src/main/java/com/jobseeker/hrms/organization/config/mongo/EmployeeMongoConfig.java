package com.jobseeker.hrms.organization.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
		basePackages = {
				"org.jobseeker.employee.repositories",
				"com.jobseeker.hrms.organization.repositories.basic.employee",
				"com.jobseeker.hrms.organization.repositories.lws.employee",
				"com.jobseeker.hrms.organization.repositories.sms.employee"
		},
		mongoTemplateRef = "employeeMongoTemplate")
@EnableConfigurationProperties
public class EmployeeMongoConfig {

}
