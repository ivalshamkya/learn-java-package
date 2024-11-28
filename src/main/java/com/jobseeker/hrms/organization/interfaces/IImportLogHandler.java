package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IImportLogHandler<T> {
    Page<T> getImportLogs(Map<Object, Object> request);
}
