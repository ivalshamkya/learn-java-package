package com.jobseeker.hrms.organization.repositories.lws.organization.department;

import org.jobseeker.organization.lawson.DepartmentLawson;
import org.jobseeker.organization.repositories.HelperOrganizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentLWSRepository extends HelperOrganizationRepository<DepartmentLawson>, DepartmentLWSRepositoryExtend {
}
