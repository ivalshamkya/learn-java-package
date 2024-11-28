package com.jobseeker.hrms.organization.data.basic.workplacement;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkplacementDataRequest {

	@NotBlank
	private String name;
}
