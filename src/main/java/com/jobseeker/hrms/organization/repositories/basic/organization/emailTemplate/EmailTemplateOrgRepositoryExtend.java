package com.jobseeker.hrms.organization.repositories.basic.organization.emailTemplate;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.jobseeker.organization.EmailTemplate;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EmailTemplateOrgRepositoryExtend {

	Optional<EmailTemplate> findFirstByActive(String positionId);

	Page<EmailTemplate> findByDataTables(PaginationParams param);
}
