package com.jobseeker.hrms.organization.repositories.lws.employee;

import org.jobseeker.employee.lawson.LawsonEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOrgLWSRepository extends MongoRepository<LawsonEmployee, String> {
    @Query(value = "{'lwsEmployment.workplacementType.workArea.branch._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByBranchId(String id);

    @Query(value = "{'lwsEmployment.lwsDepartment.division._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByDivisionId(String id);

    @Query(value = "{'lwsEmployment.lwsDepartment._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByDepartmentId(String id);

    @Query(value = "{'lwsEmployment.businessUnit._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByBusinessUnitId(String id);

    @Query(value = "{'lwsEmployment.position._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByPositionId(String id);

    @Query(value = "{'lwsEmployment.jobLevel._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByJobLevelId(String id);

    @Query(value = "{'lwsEmployment.workplacementType._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByWorkPlacementId(String id);

    @Query(value = "{'lwsEmployment.workplacementType.workArea._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByWorkAreaId(String id);
}
