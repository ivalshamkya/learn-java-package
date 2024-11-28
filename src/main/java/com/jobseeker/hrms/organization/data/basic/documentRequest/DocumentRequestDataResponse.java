package com.jobseeker.hrms.organization.data.basic.documentRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String groupCode;

	private List<String> allowedFileTypes;
}
