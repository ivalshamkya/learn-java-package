package com.jobseeker.hrms.candidate.services.basic.command;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.config.appHandler.RequestHandler;
import com.jobseeker.hrms.candidate.data.basic.request.CandidateCreateRequest;
import com.jobseeker.hrms.candidate.data.basic.request.CandidateUploadResumeRequest;
import com.jobseeker.hrms.candidate.data.basic.response.CandidateResponse;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import com.jobseeker.hrms.candidate.repository.candidate.ICandidateRepository;
import com.jobseeker.hrms.candidate.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.candidate.repositories.CandidateRepository;
import org.jobseeker.embedded.master.ExperienceDataEmbed;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.enums.general.SourceRegistered;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Service
@RequiredArgsConstructor
@Qualifier("createCandidate")
public class CreateCandidate {

    private final ICandidateRepository candidateRepository;
    private final CandidateRepository baseCandidateRepository;
    private final CompanyRepository companyRepository;

    private final UserService userService;

    private final ICandidateMapper candidateMapper;

    public CandidateResponse execute(Map<Object, Object> request) {
        CandidateCreateRequest dataRequest = RequestHandler.remapToData(request, CandidateCreateRequest.class);

        // check NIK cannot be duplicate
        if (!dataRequest.getNik().isEmpty()) {
            Candidate candidateExist = baseCandidateRepository.findByIdentityTypeNumber(dataRequest.getNik()).orElse(null);
            if (candidateExist != null) throw new IllegalArgumentException("Candidate with this NIK already exists");
        }

        Candidate candidate = candidateMapper.toSave(dataRequest);
        return registerCandidate(candidate, dataRequest.getPassword());
    }

    public CandidateResponse executeFromUploadResume(Map<Object, Object> request) {
        CandidateUploadResumeRequest dataRequest = RequestHandler.remapToData(request, CandidateUploadResumeRequest.class);

        Candidate candidate = candidateMapper.toSave(dataRequest);
        return registerCandidate(candidate, dataRequest.getPassword());
    }

    private CandidateResponse registerCandidate(Candidate candidate, String password) {

        String origin = ServletRequestAWS.getOrigin();
        Company company = ServletRequestAWS.getSourceApp().contains("careersite") ?
                companyRepository.findByCSiteOrHrmsURL(origin).orElseThrow(() -> new NoSuchElementException("Careersite url not found")) :
                companyRepository.findByHrmsURL(origin).orElseThrow(() -> new NoSuchElementException("Hrms url not found"));

        CompanyDataEmbed dataEmbed = new CompanyDataEmbed();
        dataEmbed.set_id(company.get_id());
        dataEmbed.setName(company.getName());

        int totalExperiences = Optional.of(candidate.getExperiences())
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .mapToInt(ExperienceDataEmbed::getWorkingPeriodInMonths)
                .sum();
        candidate.setTotalExperience(totalExperiences);

        candidate.setLastEducation(candidate.getEducations().getFirst());
        candidate.setRegisteredFrom(ServletRequestAWS.getSourceApp().contains("careersite") ? SourceRegistered.CAREER_SITE : SourceRegistered.JOBSEEKER_SOFTWARE);

        List<CompanyDataEmbed> fromEmployer = new ArrayList<>();
        fromEmployer.add(dataEmbed);
        candidate.setFromEmployer(fromEmployer);

        candidate.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        candidate.setCreatedAt(LocalDateTime.now());

        Candidate result = candidateRepository.save(candidate);

        userService.addNewUserDependtOnCandidate(candidate, password);

        return candidateMapper.toResponse(result);
    }
}
