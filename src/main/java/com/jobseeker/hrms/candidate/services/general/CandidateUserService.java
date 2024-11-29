package com.jobseeker.hrms.candidate.services.general;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import org.jobseeker.auth.User;
import org.jobseeker.auth.repositories.UserRepository;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.enums.msgBroker.MessageBrokerType;
import org.jobseeker.msgBroker.ProducerLog;
import org.jobseeker.msgBroker.repositories.ProducerLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateUserService {

	private final UserRepository userRepository;
	private final ProducerLogRepository producerLogRepository;
	private final SnsClient snsClient;

	@Value("${sns.create-user-topic-arn}")
	private String sendMailTopic;

	@Value("${sns.data-update-topic-arn}")
	private String dataUpdateTopic;

	public void createUserDependtOnCandidate(Candidate candidate, String password) {
		Map<String, Object> message = new HashMap<>();
		message.put("type", MessageBrokerType.NUFC.name());
		message.put("objectId", candidate.get_id());
		message.put("randomString", password);
		message.put("lang", ServletRequestAWS.getLanguage());

		ProducerLog log = createLog(candidate, MessageBrokerType.NUFC.getLabel());

		try {
			PublishRequest publishRequest = PublishRequest
					.builder()
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

	private ProducerLog createLog(Candidate candidate, String type) {
		String userName = Optional.of(candidate.getName()).orElse("null");
		String companyName = Optional.of(candidate.getFromEmployer().getFirst().getName()).orElse("null");

		ProducerLog log = new ProducerLog();
		log.setType(type);
		log.setObjectId(candidate.get_id());
		log.setDetailMsg("Create User Candidate: " + userName + ", from Employer: "
				+ companyName);

		return log;
	}

	@Transactional
	public void updateEmailUserDepentOnCandidate(Candidate candidate, String lastKnownEmail) {
		User user = userRepository.findByEmail(lastKnownEmail)
				.orElseThrow(() -> new NoSuchElementException("Cannot update user candidate because data has not found."));

		user.setEmail(candidate.getEmail());
		userRepository.save(user);
	}

	public void triggerDataUpdate(String oid, String type) {
		try {
			// send topic for send mail to candidate
			HashMap<String, Object> message = new HashMap<>();
			message.put("type", type);
			message.put("objectId", oid);

			PublishRequest publishRequest = PublishRequest
					.builder()
					.topicArn(dataUpdateTopic)
					.message(message.toString())
					.build();

			PublishResponse publishResponse = snsClient.publish(publishRequest);
			System.out.println(publishResponse);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
	}
}
