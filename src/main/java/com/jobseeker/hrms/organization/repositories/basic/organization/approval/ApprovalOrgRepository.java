package com.jobseeker.hrms.organization.repositories.basic.organization.approval;

import org.jobseeker.organization.Approval;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalOrgRepository extends MongoRepository<Approval, String>, ApprovalOrgRepositoryExtend {
    void deleteByEmployee__id(String employeeId);

    @Query("{'employee._id': ?0, 'company._id': ?1, 'status': ?2, 'deleted_at': null}")
    Optional<Approval> existsByCompanyAndEmployeeAndStatus(String companyId, String employeeId, String status);

    @Query("{ 'company._id': ?0, 'deleted_at': null }")
    Optional<List<Approval>> findByCompanyId(String companyId);

    @Query("{ 'company._id': ?0, 'type': ?1, 'deleted_at': null }")
    Optional<List<Approval>> findByCompanyId(String companyId, String type);
}
