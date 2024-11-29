package com.jobseeker.hrms.candidate.data.general.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String provider;

	private String username;

	private String link;
}
