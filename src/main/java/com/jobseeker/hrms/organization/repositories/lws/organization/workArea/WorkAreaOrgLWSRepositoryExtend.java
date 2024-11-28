package com.jobseeker.hrms.organization.repositories.lws.organization.workArea;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface WorkAreaOrgLWSRepositoryExtend {

	Optional<WorkAreaLawson> findFirstByActive(String positionId);

	Page<WorkAreaLawson> findByDataTables(PaginationLWSParams param);
}
