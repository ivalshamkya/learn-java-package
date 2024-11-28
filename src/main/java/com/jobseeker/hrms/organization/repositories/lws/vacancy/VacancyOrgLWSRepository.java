package com.jobseeker.hrms.organization.repositories.lws.vacancy;

import org.bson.types.ObjectId;
import org.jobseeker.vacancy.lawson.LawsonVacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyOrgLWSRepository extends MongoRepository<LawsonVacancy, String>, VacancyOrgLWSRepositoryAdd {
    @Query(value = "{'areaPlacementRankList.workPlacement.workArea.branch._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByBranchId(String id);

    @Query(value = "{'department.division._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByDivisionId(ObjectId id);

    @Query(value = "{'department._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByDepartmentId(String id);

    @Query(value = "{'businessUnit._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByBusinessUnitId(String id);

    @Query(value = "{'jobMaster._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByJobMasterId(String id);

    @Query(value = "{'jobLevel._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByJobLevelId(String id);

    @Query(value = "{'jobType._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByJobTypeId(String id);

    @Query(value = "{'areaPlacementRankList.workPlacement._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByWorkPlacementId(String id);

    @Query(value = "{'areaPlacementRankList.workPlacement.workArea._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByWorkAreaId(String id);

    @Query(value = "{'softSkillsRequirement.types._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsBySoftSkillId(String id);

    @Query(value = "{'hardSkillsRequirement.types._id': ?0, status: 'ACTIVE' }", exists = true)
    boolean existsByHardSkillId(String id);
}
