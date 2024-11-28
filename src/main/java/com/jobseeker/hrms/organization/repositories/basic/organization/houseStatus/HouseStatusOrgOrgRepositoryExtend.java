package com.jobseeker.hrms.organization.repositories.basic.organization.houseStatus;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.HouseStatus;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface HouseStatusOrgOrgRepositoryExtend {
    Page<HouseStatus> findByDataTables(PaginationParam param);

    Optional<HouseStatus> findFirstByActive(String houseStatusId);
}
