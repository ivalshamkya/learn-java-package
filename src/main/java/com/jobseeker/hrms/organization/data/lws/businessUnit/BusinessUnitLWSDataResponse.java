package com.jobseeker.hrms.organization.data.lws.businessUnit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUnitLWSDataResponse {
	@JsonProperty("oid")
	private String _id;
	private String name;
	private String code;
	private Integer slaDuration;
}
