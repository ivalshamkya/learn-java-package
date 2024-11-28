package com.jobseeker.hrms.organization.data.lws.position;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionLWSDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String code;

	@Min(value = 1)
	private Integer grade;

	private String vacancyDescription;

	@NotBlank
	private String jobFunctionId;
}
