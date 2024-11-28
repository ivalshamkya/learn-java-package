package com.jobseeker.hrms.organization.repositories.basic.organization.disability;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.DisabilityOrganization;
import org.springframework.data.domain.Page;

public interface DisabilityOrgOrgRepositoryExtend {
    Page<DisabilityOrganization> findByDataTables(PaginationParam param);
}
