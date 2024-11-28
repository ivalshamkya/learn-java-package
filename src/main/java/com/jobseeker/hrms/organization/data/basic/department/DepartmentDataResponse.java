package com.jobseeker.hrms.organization.data.basic.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobseeker.hrms.organization.config.timezone.ConvertToClientTimeZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String code;

	@ConvertToClientTimeZone
	private LocalDateTime createdAt;
}
