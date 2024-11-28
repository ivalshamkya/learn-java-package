package com.jobseeker.hrms.organization.data.lws.branch;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LawsonBranchDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String address;

	@NotBlank
	private String cityId;

	private String longlat;

	// custom
	@NotBlank
	private String code;

}
