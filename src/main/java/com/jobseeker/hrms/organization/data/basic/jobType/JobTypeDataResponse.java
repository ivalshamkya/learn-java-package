package com.jobseeker.hrms.organization.data.basic.jobType;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobTypeDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String statusEmployment;
}
