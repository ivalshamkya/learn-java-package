package com.jobseeker.hrms.candidate.services.general.logging;

import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.employee.Employee;
import org.jobseeker.msgBroker.ProducerLog;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogingProducerService {

	public ProducerLog createLog(Object object, String type) {
		ProducerLog log = new ProducerLog();
		log.setType(type);
		if (object instanceof Employee employee) {
			String userName = Optional.of(employee.getName()).orElse("null");
			String companyName = Optional.of(employee.getCompany())
					.map(CompanyDataEmbed::getName).orElse("null");
			String companyCode = Optional.of(employee.getCompany())
					.map(CompanyDataEmbed::getCode).orElse("null");
			log.setObjectId(employee.get_id());
			log.setDetailMsg("Send mail to new User Employee: " + userName + ", from Employer: "
					+ companyName + " / " + companyCode);
		} else if (object instanceof Candidate candidate) {
			String userName = Optional.of(candidate.getName()).orElse("null");
			String companyName = Optional.of(candidate.getFromEmployer().getLast())
					.map(CompanyDataEmbed::getName).orElse("null");
			log.setObjectId(candidate.get_id());
			log.setDetailMsg("Send mail to new User Candidate: " + userName + ", from Employer: "
					+ companyName);
		}
		return log;
	}

}
