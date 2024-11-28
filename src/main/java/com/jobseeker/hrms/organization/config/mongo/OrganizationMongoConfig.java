package com.jobseeker.hrms.organization.config.mongo;

import org.jobseeker.config.repository.JSCRepositoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
		basePackages = {
				"com.jobseeker.hrms.organization.repositories.basic.organization",
				"com.jobseeker.hrms.organization.repositories.lws.organization",
				"com.jobseeker.hrms.organization.repositories.sms.organization",
				"org.jobseeker.organization",
		},
		mongoTemplateRef = "organizationMongoTemplate",
		repositoryBaseClass = JSCRepositoryImpl.class
)
@EnableConfigurationProperties
public class OrganizationMongoConfig {

}