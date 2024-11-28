package com.jobseeker.hrms.organization.data.basic.recruitmentStage;

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
public class RecruitmentStageDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Pattern(regexp = "^(INTERVIEW|ASSESSMENT)$",
			message = "Field allowed input: INTERVIEW or ASSESSMENT")
	private String type;
}
