package com.jobseeker.hrms.organization.repositories.basic.organization.position;

import org.jobseeker.organization.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionOrgRepository extends MongoRepository<Position, String>, PositionOrgRepositoryExtend {
}
