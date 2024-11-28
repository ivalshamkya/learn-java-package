package com.jobseeker.hrms.organization.data.lws.workArea;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkAreaLWSDataRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String branchId;
}
