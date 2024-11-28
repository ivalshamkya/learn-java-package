package com.jobseeker.hrms.organization.data.basic.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String code;
}
