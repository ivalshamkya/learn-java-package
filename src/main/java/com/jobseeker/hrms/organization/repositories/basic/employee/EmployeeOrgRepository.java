package com.jobseeker.hrms.organization.repositories.basic.employee;

import org.jobseeker.employee.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeOrgRepository extends MongoRepository<Employee, String> {

	@Query(value = "{ '$and': [{'company._id':  ?0}, {'status': 'ACTIVE'}, {'deletedAt':  null}] }", count = true)
	Optional<Long> countByCompany(String companyId);

	@Query(value = "{ '$and': [{'employment.branch._id':  ?0}, {'status': 'ACTIVE'}, {'deletedAt':  null}] }", count = true)
	Optional<Long> countByBranch(String branchId);

	@Query(value = "{?0: ?1, 'status': 'ACTIVE'}", exists = true)
	boolean isExist(String query, Object id);

}
