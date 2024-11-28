package com.jobseeker.hrms.candidate.data.general.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GenderDataResponse {

    @JsonProperty("oid")
    private String _id;

    private String name;

}
