package com.jobseeker.hrms.organization.data.basic.jobType;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobTypeDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Pattern(regexp = "^(ACTIVE|INACTIVE)$",
			message = "Field allowed input: ACTIVE or INACTIVE")
	private String statusEmployment;
}
