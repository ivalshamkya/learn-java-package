package com.jobseeker.hrms.organization.repositories.sms.organization.importLog;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.jobseeker.logs.ImportLog;
import org.springframework.data.domain.Page;

public interface ImportLogSMSRepositoryExtend {
    Page<ImportLog> findDataTables(PaginationParams params);
}
