package com.jobseeker.hrms.organization.data.lws.recruitmentStage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentStageDataLWSRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Pattern(regexp = "^(SELECTION|ADMINISTRATION)$",
			message = "Field allowed input: SELECTION or ADMINISTRATION")
	private String type;
}
