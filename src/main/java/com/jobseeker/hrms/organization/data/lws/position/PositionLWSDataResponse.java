package com.jobseeker.hrms.organization.data.lws.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionLWSDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String code;

	private Integer grade;

	private String vacancyDescription;

	private GeneralDataEmbed jobFunction;
}
