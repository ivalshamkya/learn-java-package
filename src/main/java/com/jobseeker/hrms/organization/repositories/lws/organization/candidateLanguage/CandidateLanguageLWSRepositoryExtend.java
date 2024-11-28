package com.jobseeker.hrms.organization.repositories.lws.organization.candidateLanguage;


import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import org.jobseeker.organization.CandidateLanguage;
import org.springframework.data.domain.Page;

public interface CandidateLanguageLWSRepositoryExtend {
    Page<CandidateLanguage> findDataTables(PaginationLWSParams params);
}
