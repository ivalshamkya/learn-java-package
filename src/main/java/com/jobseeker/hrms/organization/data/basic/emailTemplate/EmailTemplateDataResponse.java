package com.jobseeker.hrms.organization.data.basic.emailTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateDataResponse {
	@JsonProperty("oid")
	private String _id;
	private String name;
	private String subject;
	private String content;
}
