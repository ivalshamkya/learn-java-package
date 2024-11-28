package com.jobseeker.hrms.organization.repositories.lws.organization.workplacement;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface WorkplacementOrgLWSRepositoryExtend {

	Optional<WorkplacementLawson> findFirstByActive(String positionId);

	Page<WorkplacementLawson> findByDataTables(PaginationLWSParams param);
}
