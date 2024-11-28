package com.jobseeker.hrms.organization.data.sms.jobTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobseeker.hrms.organization.config.timezone.ConvertToClientTimeZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobTemplateDataPagingResponse {

	@JsonProperty("oid")
	private String _id;

	@JsonProperty("jobName")
	private String vacancyName;

	private GeneralDataEmbed department;

	@ConvertToClientTimeZone
	private LocalDateTime createdAt;
}