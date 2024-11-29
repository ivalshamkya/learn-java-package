package com.jobseeker.hrms.candidate.data.basic.response.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jobseeker.hrms.candidate.config.serializer.LocalDateTimeTimezoneSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.enums.vacancy.applicant.ApplyActivityType;
import org.jobseeker.enums.vacancy.applicant.ApplyStatusType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantActivityDataResponse {

    @JsonProperty("oid")
    private String _id;
    private String applyProcess;
    private ApplyStatusType status;
    private ApplyActivityType type;
    private Boolean inReview;
    private String notes;
    private String from;
    private String file;
    private EmployeeDataEmbed actionBy;

    @JsonSerialize(using = LocalDateTimeTimezoneSerializer.class)
    private LocalDateTime processAt;
    @JsonSerialize(using = LocalDateTimeTimezoneSerializer.class)
    private LocalDateTime actionAt;
    @JsonSerialize(using = LocalDateTimeTimezoneSerializer.class)
    private LocalDateTime expiredAt;
    @JsonSerialize(using = LocalDateTimeTimezoneSerializer.class)
    private LocalDateTime cvDate;
}
