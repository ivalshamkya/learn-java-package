package com.jobseeker.hrms.organization.repositories.lws.organization.department;

import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.lawson.DepartmentLawson;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.data.domain.Page;


public interface DepartmentLWSRepositoryExtend {

	Page<DepartmentLawson> findByDataTables(PaginationLWSParams param);
}
