package com.jobseeker.hrms.organization.repositories.basic.organization.reasonOrganization;

import com.jobseeker.hrms.organization.data.basic.reasonOrganization.ReasonOrganizationDataRequest;
import org.jobseeker.organization.ReasonOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReasonOrganizationOrgOrgRepositoryExtend {
    Page<ReasonOrganization> findDataTables(ReasonOrganizationDataRequest params);
}
