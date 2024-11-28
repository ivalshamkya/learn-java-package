package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IImportLogHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseImportLog<T> implements IImportLogHandler<T> {
    @Override
    public Page<T> getImportLogs(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Get candidate languages not supported yet.");
    }
}
