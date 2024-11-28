package com.jobseeker.hrms.candidate.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
		basePackages = {
				"org.jobseeker.auth.repositories"
		},
		mongoTemplateRef = "authMongoTemplate")
@EnableConfigurationProperties
public class AuthMongoConfig {
}