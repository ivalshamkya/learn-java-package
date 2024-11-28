package com.jobseeker.hrms.organization.config.mongo;

import org.jobseeker.config.repository.JSCRepositoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {
                "org.jobseeker.logs.repositories"
        },
        mongoTemplateRef = "logsMongoTemplate",
        repositoryBaseClass = JSCRepositoryImpl.class)
@EnableConfigurationProperties
public class ImportLogMongoConfig {

}
