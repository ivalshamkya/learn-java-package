package com.jobseeker.hrms.candidate.config.appHandler;

import com.jobseeker.hrms.candidate.services.BaseCandidateService;
import com.jobseeker.hrms.candidate.services.BaseCareersiteService;
import com.jobseeker.hrms.candidate.services.basic.CandidateService;
import com.jobseeker.hrms.candidate.services.basic.CareersiteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.jobseeker.hrms.candidate")
public class AppRegistryHandler {

	//<editor-fold desc="Initialize Source App">
	private final String HRMS_BASIC;

	public AppRegistryHandler() {
		HRMS_BASIC  = "hrms-basic";
	}
	//</editor-fold>

	@Bean(name = "candidateHandlerMap")
	public Map<String, BaseCandidateService<?>> applicantHandlerMapConfig(
			CandidateService<?> applicantService
	) {
		Map<String, BaseCandidateService<?>> applicantMap = new HashMap<>();

		applicantMap.put(HRMS_BASIC, applicantService);

		return applicantMap;
	}

	@Bean(name = "careersiteHandlerMap")
	public Map<String, BaseCareersiteService<?>> careersiteHandlerMapConfig(
			CareersiteService<?> careersiteService
	) {
		Map<String, BaseCareersiteService<?>> careersiteMap = new HashMap<>();
		careersiteMap.put("careersite", careersiteService); // basic
		return careersiteMap;
	}
}
