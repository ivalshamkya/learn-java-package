package com.jobseeker.hrms.organization.config.mongo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {"org.jobseeker.notification"},
        mongoTemplateRef = "notificationMongoTemplate"
)
@EnableConfigurationProperties
public class NotificationMongoConfig {

}
