package com.jobseeker.hrms.organization.data.sms.workArea;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkAreaSmsDataRequest {
	@NotBlank
	private String name;
}