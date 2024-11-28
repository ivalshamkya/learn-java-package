package com.jobseeker.hrms.organization.data.sms.jobTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobTemplateDataRequest {

	@NotBlank
	@JsonProperty("jobName")
	private String vacancyName;

	@NotBlank
	@JsonProperty("departmentId")
	private String departmentId;

	@NotBlank
	@JsonProperty("jobDescription")
	private String vacancyDescription;

	@NotBlank
	@JsonProperty("jobTypeId")
	private String jobTypeId;

	@NotBlank
	@JsonProperty("jobLevelId")
	private String jobLevelId;

	@NotBlank
	@JsonProperty("jobQualification")
	private String vacancyQualification;

}