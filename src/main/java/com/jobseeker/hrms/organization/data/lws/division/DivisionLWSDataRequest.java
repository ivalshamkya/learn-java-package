package com.jobseeker.hrms.organization.data.lws.division;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivisionLWSDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String code;

}
