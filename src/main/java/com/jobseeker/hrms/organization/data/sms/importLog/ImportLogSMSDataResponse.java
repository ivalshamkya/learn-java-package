package com.jobseeker.hrms.organization.data.sms.importLog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportLogSMSDataResponse {
    @JsonProperty("oid")
    private String _id;

    private String fileName;

    private String importedAt;

    private String status;

    private String totalErrors;

    private String totalSuccess;

    private String totalProcessed;

    private EmployeeDataEmbed employee;

    private String errorLogUrl;
}
