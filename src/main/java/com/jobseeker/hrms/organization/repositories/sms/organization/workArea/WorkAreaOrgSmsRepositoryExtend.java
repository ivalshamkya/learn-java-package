package com.jobseeker.hrms.organization.repositories.sms.organization.workArea;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.sms.WorkAreaSMS;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface WorkAreaOrgSmsRepositoryExtend {

	Optional<WorkAreaSMS> findFirstByActive(String positionId);

	Page<WorkAreaSMS> findByDataTables(PaginationParam param);
}
