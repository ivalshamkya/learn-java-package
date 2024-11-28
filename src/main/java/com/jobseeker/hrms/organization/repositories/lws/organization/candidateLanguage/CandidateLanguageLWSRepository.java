package com.jobseeker.hrms.organization.repositories.lws.organization.candidateLanguage;

import org.jobseeker.organization.CandidateLanguage;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateLanguageLWSRepository extends HelperOrganizationRepository<CandidateLanguage>, CandidateLanguageLWSRepositoryExtend {
}

