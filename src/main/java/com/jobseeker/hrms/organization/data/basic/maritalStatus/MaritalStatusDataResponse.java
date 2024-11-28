package com.jobseeker.hrms.organization.data.basic.maritalStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaritalStatusDataResponse {
    @JsonProperty("oid")
    private String _id;
    
    private Object name;
    
}
