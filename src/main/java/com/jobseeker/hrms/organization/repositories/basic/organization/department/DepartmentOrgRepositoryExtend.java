package com.jobseeker.hrms.organization.repositories.basic.organization.department;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Department;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DepartmentOrgRepositoryExtend {

	Optional<Department> findFirstByActive(String departmentId);

	Page<Department> findByDataTables(PaginationParam param);
}
