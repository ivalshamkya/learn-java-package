package com.jobseeker.hrms.organization.data.lws.businessUnit;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUnitLWSDataRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String code;
	@NotNull
	private Integer slaDuration;
}
