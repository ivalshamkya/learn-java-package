package com.jobseeker.hrms.organization.repositories.basic.organization.vacancyTemplate.sms;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.sms.VacancyTemplateSMS;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface VacancyTemplateSMSRepositoryExtend {

	Optional<VacancyTemplateSMS> findFirstByActive(String vacancyTemplateSMSId);

	Page<VacancyTemplateSMS> findByDataTables(PaginationParam param);

}
