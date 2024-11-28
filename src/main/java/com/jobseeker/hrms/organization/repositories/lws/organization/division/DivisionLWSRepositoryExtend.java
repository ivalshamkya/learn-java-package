package com.jobseeker.hrms.organization.repositories.lws.organization.division;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Department;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface DivisionLWSRepositoryExtend {

	Page<DivisionLawson> findByDataTables(PaginationParam param);
	Optional<DivisionLawson> findFirstByActive(String divisionId);
}
