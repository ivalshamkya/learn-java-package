package com.jobseeker.hrms.candidate.services.general.logging;

import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.msgBroker.MessageBrokerType;
import org.jobseeker.msgBroker.ConsumerLog;
import org.jobseeker.msgBroker.repositories.ConsumerLogRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LogingConsumerService {

	private final ConsumerLogRepository consumerLogRepository;

	public void logAddNewUserEmployee(String oid, boolean status, String msg) {
		ConsumerLog consumerLog = new ConsumerLog();

		consumerLog.setProducerType(MessageBrokerType.NUFE.getLabel());
		consumerLog.setObjectId(oid);
		consumerLog.setIsSuccess(status);
		consumerLog.setReason(msg);
		consumerLog.setCreatedAt(LocalDateTime.now());
		consumerLogRepository.save(consumerLog);
	}

	public void logAddNewUserCandidate(String oid, boolean status, String msg) {
		ConsumerLog consumerLog = new ConsumerLog();

		consumerLog.setProducerType(MessageBrokerType.NUFC.getLabel());
		consumerLog.setObjectId(oid);
		consumerLog.setIsSuccess(status);
		consumerLog.setReason(msg);
		consumerLog.setCreatedAt(LocalDateTime.now());
		consumerLogRepository.save(consumerLog);
	}

}
