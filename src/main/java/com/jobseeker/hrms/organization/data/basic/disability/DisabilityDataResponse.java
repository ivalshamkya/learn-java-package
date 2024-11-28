package com.jobseeker.hrms.organization.data.basic.disability;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisabilityDataResponse {
    @JsonProperty("oid")
    private String _id;
    
    private Object name;
    
    private String code;
}
