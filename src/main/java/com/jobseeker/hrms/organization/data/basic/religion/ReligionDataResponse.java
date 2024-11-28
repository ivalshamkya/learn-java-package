package com.jobseeker.hrms.organization.data.basic.religion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReligionDataResponse {
    @JsonProperty("oid")
    private String _id;
    
    private Object name;
    
}
