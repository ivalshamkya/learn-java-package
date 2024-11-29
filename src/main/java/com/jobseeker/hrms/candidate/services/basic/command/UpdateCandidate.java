package com.jobseeker.hrms.candidate.services.basic.command;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.config.appHandler.RequestHandler;
import com.jobseeker.hrms.candidate.data.basic.request.update.CandidateUpdateEducationRequest;
import com.jobseeker.hrms.candidate.data.basic.request.update.CandidateUpdatePersonalInformationRequest;
import com.jobseeker.hrms.candidate.data.basic.request.update.CandidateUpdateWorkingExperienceRequest;
import com.jobseeker.hrms.candidate.data.basic.response.CandidateResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import com.jobseeker.hrms.candidate.repository.applicant.IApplicantRepository;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import com.jobseeker.hrms.candidate.services.general.CandidateUserService;
import lombok.RequiredArgsConstructor;
import org.jobseeker.auth.repositories.UserRepository;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.master.ExperienceDataEmbed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
@Qualifier("updateCandidate")
public class UpdateCandidate {

    private final ICandidateRepository candidateRepository;
    private final IApplicantRepository applicantRepository;
    private final UserRepository userRepository;

    private final ICandidateMapper candidateMapper;
    private final CandidateUserService candidateUserService;

    public CandidateResponse execute(Map<Object, Object> request, String step) {
        try {
            Candidate candidate = candidateRepository.findById(ServletRequestAWS.getCandidateId())
                    .orElseThrow(() -> new NoSuchElementException("No candidate found"));

            switch (step) {
                case "personal_information" -> {
                    CandidateUpdatePersonalInformationRequest dataRequest = RequestHandler.remapToData(request, CandidateUpdatePersonalInformationRequest.class);

                    String lastKnownEmail = candidate.getEmail();

                    candidateMapper.toUpdatePersonalInformation(candidate, dataRequest);

                    String changedEmail = dataRequest.getEmail();

                    if (!candidate.getEmail().equals(changedEmail)) {
                        userRepository.findByEmail(changedEmail)
                                .ifPresentOrElse(
                                        userExist -> {
                                            throw new IllegalArgumentException("Email " + userExist.getEmail() + " already exists");
                                        },
                                        () -> {}
                                );
                    }

                    // update data email pada user, cara ini sedikit kuno perlu dioptimasi kedepannya
                    candidateUserService.updateEmailUserDepentOnCandidate(candidate, lastKnownEmail);
                }
                case "working_experience" -> {
                    CandidateUpdateWorkingExperienceRequest dataRequest = RequestHandler.remapToData(request, CandidateUpdateWorkingExperienceRequest.class);
                    candidateMapper.toUpdateWorkingExperiences(candidate, dataRequest);

                    int totalExperiences = Optional.ofNullable(candidate.getExperiences())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .filter(Objects::nonNull)
                            .mapToInt(ExperienceDataEmbed::getWorkingPeriodInMonths)
                            .sum();
                    candidate.setTotalExperience(totalExperiences);
                }
                case "education" -> {
                    CandidateUpdateEducationRequest dataRequest = RequestHandler.remapToData(request, CandidateUpdateEducationRequest.class);
                    candidateMapper.toUpdateEducations(candidate, dataRequest);

                    if (candidate.getEducations() != null && !candidate.getEducations().isEmpty()) {
                        candidate.setLastEducation(candidate.getEducations().getFirst());
                    }
                }
                default -> {
                    return null;
                }
            }

            Date createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            candidate.setUpdatedAt(createdAt);

            candidateRepository.update(candidate, candidate.get_id());
            // update data candidate pada applicant menggunakan User Worker
            candidateUserService.triggerDataUpdate(candidate.get_id(), "UPDATE_CANDIDATE");

            return null;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
