package com.jobseeker.hrms.organization.repositories.basic.organization.customData;

import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import org.jobseeker.organization.CustomData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomDataOrgRepository {

    Page<CustomData> findByParamsPaginated(CustomDataParams params, String CompanyUrl, Pageable pageable);
}
