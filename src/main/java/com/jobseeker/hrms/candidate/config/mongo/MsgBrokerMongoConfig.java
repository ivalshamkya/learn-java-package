package com.jobseeker.hrms.candidate.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
		basePackages = {
				"org.jobseeker.msgBroker.repositories"
		},
		mongoTemplateRef = "msgbrokerMongoTemplate")
@EnableConfigurationProperties
public class MsgBrokerMongoConfig {
}