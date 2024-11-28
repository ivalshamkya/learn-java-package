package com.jobseeker.hrms.organization.repositories.basic.organization.customSource;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.jobseeker.organization.CustomSource;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CustomSourceOrgRepositoryExtend {

	Optional<CustomSource> findFirstByActive(String positionId);

	Page<CustomSource> findByDataTables(PaginationParams param);
}
