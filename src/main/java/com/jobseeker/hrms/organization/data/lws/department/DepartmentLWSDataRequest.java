package com.jobseeker.hrms.organization.data.lws.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentLWSDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String abbreviation;

	@NotBlank
	private String code;

	@NotBlank
	private String divisionId;

}
