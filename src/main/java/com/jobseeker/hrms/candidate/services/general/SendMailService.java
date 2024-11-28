package com.jobseeker.hrms.candidate.services.general;

import com.jobseeker.hrms.candidate.services.general.logging.LogingProducerService;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.mailing.lawson.LawsonSendMailType;
import org.jobseeker.logs.embedded.IncomingData;
import org.jobseeker.msgBroker.ProducerLog;
import org.jobseeker.msgBroker.repositories.ProducerLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SendMailService {

	private final SnsClient snsClient;
	private final LogingProducerService logService;
	private final ProducerLogRepository producerLogRepository;

	@Value("${sns.send-mail-topic-arn}")
	private String sendMailTopic;

	public void sendMailRegister(Object object, IncomingData data) {
		Map<String, Object> message = new HashMap<>();
		message.put("type", data.getType());
		message.put("objectId", data.getObjectId());
		message.put("randomString", data.getRandomString());

		if (data.getLang() != null) message.put("lang", data.getLang());
		ProducerLog log = logService.createLog(object, data.getType());

		try {
			PublishRequest publishRequest = PublishRequest.builder()
					.topicArn(sendMailTopic)
					.message(message.toString())
					.build();

			PublishResponse publishResponse = snsClient.publish(publishRequest);
			log.setIsSuccess(true);
			log.setMsgId(String.valueOf(publishResponse));
		} catch (Exception ex) {
			log.setIsSuccess(false);
			log.setReason(ex.getMessage());
		}

		producerLogRepository.save(log);
	}

	@Async
	public void SendMail(String oid, String companyId, String sendMailType) {
		try {
			HashMap<String, Object> message = new HashMap<>();
			message.put("type", sendMailType);
			message.put("objectId", oid);
			message.put("companyId", companyId);

			PublishRequest publishRequest = PublishRequest
					.builder()
					.topicArn(sendMailTopic)
					.message(message.toString())
					.build();

			snsClient.publish(publishRequest);
		} catch (Exception ex) {
			throw new NoSuchElementException("Fail to send reminder when send message to broker");
		}
	}
}
