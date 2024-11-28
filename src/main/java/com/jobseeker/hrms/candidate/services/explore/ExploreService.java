package com.jobseeker.hrms.candidate.services.explore;

import com.jobseeker.hrms.candidate.data.basic.request.explore.ExploreDataRequest;
import com.jobseeker.hrms.candidate.data.basic.response.explore.ExploreDataReponse;
import com.jobseeker.hrms.candidate.mappers.explore.IExploreMapper;
import com.jobseeker.hrms.candidate.repository.candidate.explore.IExploreRepository;
import com.jobseeker.hrms.candidate.repository.master.IJobFunctionsRepository;
import com.jobseeker.hrms.candidate.repository.vacancy.IVacancyRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExploreService {

    private final IExploreRepository exploreRepository;
    private final IVacancyRepository vacancyRepository;
    private final IJobFunctionsRepository jobFunctionsRepository;
    private final IExploreMapper exploreMapper;

    public Page<ExploreDataReponse> findCandidate(ExploreDataRequest exploreDataRequest) {
        List<String> jobFunctionIds = vacancyRepository.findVacanciesExistsJobFunction();
        exploreDataRequest.setJobFunctionIds(jobFunctionIds);
        exploreDataRequest.setGenderId(getGenderType(exploreDataRequest.getGenderId()));

        if (exploreDataRequest.getJobFunctionIds() != null) {
            List<String> jobFuncIdsBasedOnCategory = jobFunctionsRepository.findJobFunctionRelatedToCategory(exploreDataRequest.getIndustryTypeId());
            exploreDataRequest.setJobFuncIdsBasedOnCategory(jobFuncIdsBasedOnCategory);
        }

        Page<Candidate> results = exploreRepository.findCandidates(exploreDataRequest);
        return results.map(data -> exploreMapper.toExploreDataReponse(data, jobFunctionIds, exploreDataRequest.getKeyword()));
    }

    private String getGenderType(String genderId) {
        if (genderId == null) return null;
        return switch (genderId) {
            case "65efe26662bcce96a57001a8" -> "MALE";
            case "65efe27862bcce96a57001aa" -> "FEMALE";
            default -> null;
        };
    }
}
