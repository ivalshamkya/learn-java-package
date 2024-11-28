package com.jobseeker.hrms.candidate.config.mongo;

import org.jobseeker.config.repository.JSCRepositoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {"com.jobseeker.hrms.candidate.repository.log"},
        mongoTemplateRef = "logsMongoTemplate",
        repositoryBaseClass = JSCRepositoryImpl.class
)
@EnableConfigurationProperties
public class LogsMongoConfig {
}
