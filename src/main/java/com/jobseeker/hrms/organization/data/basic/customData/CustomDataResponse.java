package com.jobseeker.hrms.organization.data.basic.customData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomDataResponse {

	@JsonProperty("oid")
	private String _id;
	private String name;
	private String code;
}
