package com.jobseeker.hrms.candidate;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.jobseeker.hrms.candidate", "org.jobseeker", "org.jsc.dev"})
public class CandidateJobseekerApplication {

    @Value("${application.timezone:UTC}")
    private String applicationTimeZone;

    public static void main(String[] args) {
        SpringApplication.run(CandidateJobseekerApplication.class, args);
    }

    @PostConstruct
    public void executeAfterMain() {
        TimeZone.setDefault(TimeZone.getTimeZone(applicationTimeZone));
    }

}
