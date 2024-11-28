package com.jobseeker.hrms.organization.data.lws.workplacement;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkplacementLWSDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String workAreaId;

}
