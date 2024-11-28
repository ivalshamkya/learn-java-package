package com.jobseeker.hrms.organization.mapper.sms;

import com.jobseeker.hrms.organization.data.sms.importLog.ImportLogSMSDataResponse;
import org.jobseeker.logs.ImportLog;
import org.jobseeker.logs.embedded.ImportRowError;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISmsImportLogMapper {
    @Mapping(source = "file", target = "fileName", qualifiedByName = "setFileName")
    @Mapping(source = "createdAt", target = "importedAt")
    @Mapping(source = "errors", target = "totalErrors", qualifiedByName = "setTotalErrors")
    @Mapping(source = "totalRows", target = "totalProcessed")
    ImportLogSMSDataResponse toImportLogSMSDataResponse(ImportLog importLogSMSDataResponse);

    @Named("setFileName")
    default String setFileName(String file) {
        return file.substring(file.lastIndexOf("/") + 1);
    }

    @Named("setTotalErrors")
    default String setTotalErrors(List<ImportRowError> errors) {
        long total = errors.stream()
                .map(ImportRowError::getRowNumber)
                .distinct()
                .count();

        return Long.toString(total);
    }
}
