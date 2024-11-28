package com.jobseeker.hrms.organization.repositories.basic.vacancy;

import org.bson.types.ObjectId;
import org.jobseeker.vacancy.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacancyOrgRepository extends MongoRepository<Vacancy, String>, VacancyOrgRepositoryAdd {

    @Query(value = "{'branch._id':  ?0}", count = true)
    Optional<Long> countByBranchId(ObjectId branchId);

    @Query(value = "{?0: ?1, 'status': 'ACTIVE'}", exists = true)
    boolean isExist(String query, Object id);

}
