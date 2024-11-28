package com.jobseeker.hrms.organization.repositories.basic.organization.maritalStatus;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.MaritalStatusOrganization;
import org.springframework.data.domain.Page;

public interface MaritalStatusOrgOrgRepositoryExtend {
    Page<MaritalStatusOrganization> findByDataTables(PaginationParam param);
}
