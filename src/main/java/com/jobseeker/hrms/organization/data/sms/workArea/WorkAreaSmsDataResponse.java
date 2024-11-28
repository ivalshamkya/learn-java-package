package com.jobseeker.hrms.organization.data.sms.workArea;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkAreaSmsDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;
}
