package com.jobseeker.hrms.candidate.services.user;

import com.jobseeker.hrms.candidate.mappers.user.UserMapper;
import com.jobseeker.hrms.candidate.services.general.SendMailService;
import com.jobseeker.hrms.candidate.services.general.logging.LogingConsumerService;
import lombok.RequiredArgsConstructor;
import org.jobseeker.auth.Role;
import org.jobseeker.auth.User;
import org.jobseeker.auth.WordingEnums;
import org.jobseeker.auth.repositories.RoleRepository;
import org.jobseeker.auth.repositories.UserRepository;
import org.jobseeker.auth.repositories.WordingEnumsRepository;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.auth.RoleDataEmbed;
import org.jobseeker.embedded.auth.UserAsCandidateDataEmbed;
import org.jobseeker.helper.GeneralHelper;
import org.jobseeker.logs.embedded.IncomingData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final WordingEnumsRepository wordingEnumsRepository;

	private final LogingConsumerService loggingConsumerService;
	private final SendMailService sendMailService;

	private final UserMapper userMapper;

	public void addNewUserDependtOnCandidate(Candidate candidate, String password) {
		String candidateId = candidate.get_id();

		IncomingData incomingData = IncomingData.builder()
				.type("SUCCESS_CANDIDATE_SIGN_UP")
				.objectId(candidateId)
				.randomString(password)
				.build();

		boolean consumerStatus = false;
		String reasonMsg;
		try {
			Role role = roleRepository.findByCode("cdd")
					.orElseThrow(() -> new NoSuchElementException("No additional role assigned to the user."));

			WordingEnums defaultPasswordMsg = wordingEnumsRepository.findFirstByCode("existPassword");

			RoleDataEmbed newRoleDataEmbed = userMapper.toAttach(role);
			List<RoleDataEmbed> roleDataEmbeds = new ArrayList<>();
			roleDataEmbeds.add(newRoleDataEmbed);

			UserAsCandidateDataEmbed newUserCandidate = userMapper.toAttachUserCandidate(candidate);
			newUserCandidate.setAccessGranted(roleDataEmbeds);

			// Check if any email/username has been registered
			User existUser = userRepository.findByEmail(candidate.getEmail()).orElse(new User());

			UserAsCandidateDataEmbed userCandidates = Optional.ofNullable(existUser.getAsCandidate())
					.orElse(newUserCandidate);

			if (existUser.get_id() != null) {
				existUser.setAsCandidate(userCandidates);

				userRepository.save(existUser);
				sendMailRegisterCandidate(candidate, incomingData);
				reasonMsg = "Create User oid: " + existUser.get_id() + " from Candidate oid: " + candidateId;
				loggingConsumerService.logAddNewUserCandidate(candidateId, true, reasonMsg);
				return;
			}

			String[] names = GeneralHelper.splitFullName(candidate.getName());
			User user = User.builder()
					.firstName(names[0])
					.lastName(names[1])
					.email(candidate.getEmail())
					.password(passwordEncoder.encode(password))
					.asCandidate(userCandidates)
					.createdAt(LocalDateTime.now())
					.build();

			user.setCompanyId(candidate.getFromEmployer().getFirst().get_id());

			User newUser = userRepository.save(user);
			sendMailRegisterCandidate(candidate, incomingData);
			consumerStatus = true;
			reasonMsg = "Create User oid: " + newUser.get_id() + " from Candidate oid: " + candidateId;
			System.out.println(reasonMsg);
		} catch (Exception ex) {
			StackTraceElement[] stackTraceElements = ex.getStackTrace();
			reasonMsg = ex.getMessage() + " | " + stackTraceElements[0];
		}
		loggingConsumerService.logAddNewUserCandidate(candidateId, consumerStatus, reasonMsg);
	}

	protected void sendMailRegisterCandidate(Candidate candidate, IncomingData data) {
		sendMailService.sendMailRegister(candidate, data);
	}
}
