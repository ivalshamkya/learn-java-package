package com.jobseeker.hrms.candidate.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
		basePackages = {
				"com.jobseeker.hrms.candidate.repository.applicant",
				"com.jobseeker.hrms.candidate.repository.vacancy",
				"org.jobseeker.vacancy.repositories"
		},
        mongoTemplateRef = "vacancyMongoTemplate")
@EnableConfigurationProperties
public class VacancyMongoConfig {
}
