package com.jobseeker.hrms.organization.data.basic.recruitmentStage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentStageDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String type;
}
