package com.jobseeker.hrms.organization.service.lws.candidateLanguage.query;

import com.jobseeker.hrms.organization.data.lws.candidateLanguage.CandidateLanguageLWSDataResponse;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.mapper.lws.ICandidateLanguageLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.candidateLanguage.CandidateLanguageLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetCandidateLanguages")
public class LawsonGetCandidateLanguagesService {
    private final CandidateLanguageLWSRepository candidateLanguageLWSRepository;
    private final ICandidateLanguageLWSMapper candidateLanguageLWSMapper;

    public Page<CandidateLanguageLWSDataResponse> execute(PaginationLWSParams params){
        return candidateLanguageLWSRepository.findDataTables(params)
                .map(candidateLanguageLWSMapper::toCandidateLanguageLWSDataResponse);
    }
}
