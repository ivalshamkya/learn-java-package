package com.jobseeker.hrms.organization;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.jobseeker.hrms.organization", "org.jobseeker", "org.jsc.dev"})
public class OrganizationHrmsApiApplication {

	@Value("${application.timezone:UTC}")
	private String applicationTimeZone;

	public static void main(String[] args) {
		SpringApplication.run(OrganizationHrmsApiApplication.class, args);
	}

	@PostConstruct
	public void executeAfterMain() {
		TimeZone.setDefault(TimeZone.getTimeZone(applicationTimeZone));
	}

}
