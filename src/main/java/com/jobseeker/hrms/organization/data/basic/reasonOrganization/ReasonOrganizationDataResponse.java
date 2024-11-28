package com.jobseeker.hrms.organization.data.basic.reasonOrganization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.enums.general.StatusData;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReasonOrganizationDataResponse {
    @JsonProperty("oid")
    private String _id;
    
    private Object name;
    
    private String type;
}
