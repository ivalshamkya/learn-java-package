package com.jobseeker.hrms.organization.repositories.basic.organization.department;

import org.jobseeker.organization.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentOrgRepository extends MongoRepository<Department, String>, DepartmentOrgRepositoryExtend {
}
