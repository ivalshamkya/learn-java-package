package com.jobseeker.hrms.organization.data.sms.branch;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchSmsDataRequest {

	@NotBlank
	private String code;
	@NotBlank
	private String name;
//	@NotBlank
//	private String groupCode;
	@NotBlank
	private String workAreaId;

}
