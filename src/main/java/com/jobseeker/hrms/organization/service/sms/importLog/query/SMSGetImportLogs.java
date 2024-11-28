package com.jobseeker.hrms.organization.service.sms.importLog.query;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.data.sms.importLog.ImportLogSMSDataResponse;
import com.jobseeker.hrms.organization.mapper.sms.ISmsImportLogMapper;
import com.jobseeker.hrms.organization.repositories.sms.organization.importLog.ImportLogSMSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("SMSGetImportLogs")
public class SMSGetImportLogs {
    private final ImportLogSMSRepository importLogSMSRepository;
    private final ISmsImportLogMapper importLogMapper;

    public Page<ImportLogSMSDataResponse> execute(PaginationParams paginationParams) {
        return  importLogSMSRepository.findDataTables(paginationParams)
                    .map(importLogMapper::toImportLogSMSDataResponse);
    }
}
