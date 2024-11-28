package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.candidateLanguage.CandidateLanguageLWSDataResponse;
import org.jobseeker.organization.CandidateLanguage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICandidateLanguageLWSMapper {
    CandidateLanguageLWSDataResponse toCandidateLanguageLWSDataResponse(CandidateLanguage candidateLanguage);
}
