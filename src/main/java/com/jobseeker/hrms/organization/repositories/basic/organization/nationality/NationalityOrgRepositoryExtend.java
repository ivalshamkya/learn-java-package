package com.jobseeker.hrms.organization.repositories.basic.organization.nationality;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Nationality;
import org.springframework.data.domain.Page;

public interface NationalityOrgRepositoryExtend {
    Page<Nationality> findByDataTables(PaginationParam param);
}
