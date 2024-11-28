package com.jobseeker.hrms.organization.service.sms.importLog;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.service.baseContract.BaseImportLog;
import com.jobseeker.hrms.organization.service.sms.importLog.query.SMSGetImportLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class ImportLogSMSService<T> extends BaseImportLog<T> {
    @Autowired
    @Qualifier("SMSGetImportLogs")
    private SMSGetImportLogs smsGetImportLogs;

    @Override
    public Page<T> getImportLogs(Map<Object, Object> param) {
        PaginationParams dataRequest = RequestHandler.remapToData(param, PaginationParams.class);
        return (Page<T>) smsGetImportLogs.execute(dataRequest);
    }


}
