package com.jobseeker.hrms.organization.repositories.basic.organization.customGroup;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.jobseeker.organization.CustomGroupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomGroupDataOrgRepository {
    Page<CustomGroupData> findByParamsPaginated(PaginationParams params, Pageable pageable);
}
