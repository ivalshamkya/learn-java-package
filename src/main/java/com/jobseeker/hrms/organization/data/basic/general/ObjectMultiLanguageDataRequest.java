package com.jobseeker.hrms.organization.data.basic.general;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectMultiLanguageDataRequest {
	@NotBlank
	private String en;

	@NotBlank
	private String id;

}
