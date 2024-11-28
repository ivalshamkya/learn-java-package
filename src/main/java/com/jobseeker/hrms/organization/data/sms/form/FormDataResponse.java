package com.jobseeker.hrms.organization.data.sms.form;

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
public class FormDataResponse {

	@JsonProperty("groupName")
	private String group_name;

	@JsonProperty("items")
	private List<FormItem> items;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FormItem {

		@JsonProperty("oid")
		private String id;

		@JsonProperty("name")
		private String name;

		@JsonProperty("desc")
		private String desc;

		@JsonProperty("groupSequence")
		private Integer group_sequence;

		@JsonProperty("sequenceItem")
		private Integer sequence;

		@JsonProperty("valueType")
		private String value_type;
	}
}
