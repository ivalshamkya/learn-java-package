package com.jobseeker.hrms.candidate.config.mongo;

import org.jobseeker.config.repository.JSCRepositoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {
                "com.jobseeker.hrms.candidate.repository.candidate",
                "org.jobseeker.candidate"
        },
        mongoTemplateRef = "candidateMongoTemplate",
        repositoryBaseClass = JSCRepositoryImpl.class
)
@EnableConfigurationProperties
public class CandidateMongoConfig {
}
