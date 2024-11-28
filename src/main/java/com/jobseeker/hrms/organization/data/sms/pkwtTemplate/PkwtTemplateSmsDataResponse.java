package com.jobseeker.hrms.organization.data.sms.pkwtTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PkwtTemplateSmsDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private List<String> benefits;
}