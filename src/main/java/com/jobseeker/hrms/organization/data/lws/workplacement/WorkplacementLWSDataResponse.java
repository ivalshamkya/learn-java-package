package com.jobseeker.hrms.organization.data.lws.workplacement;

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
public class WorkplacementLWSDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private GeneralDataEmbed workArea;

	@ConvertToClientTimeZone
	private LocalDateTime createdAt;
}
