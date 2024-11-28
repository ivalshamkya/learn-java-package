package com.jobseeker.hrms.organization.repositories.lws.organization.businessUnit;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import org.jobseeker.organization.lawson.BusinessUnitLawson;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BusinessUnitOrgLWSRepositoryExtend {

	Optional<BusinessUnitLawson> findFirstByActive(String positionId);

	Page<BusinessUnitLawson> findByDataTables(PaginationLWSParams param);
}
