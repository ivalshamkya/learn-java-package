package com.jobseeker.hrms.organization.repositories.basic.organization.religionOrganization;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.ReligionOrganization;
import org.springframework.data.domain.Page;

public interface ReligionOrganizationOrgOrgRepositoryExtend {
    Page<ReligionOrganization> findDataTables(PaginationParam params);
}
