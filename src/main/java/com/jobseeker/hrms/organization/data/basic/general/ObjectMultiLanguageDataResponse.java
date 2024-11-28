package com.jobseeker.hrms.organization.data.basic.general;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectMultiLanguageDataResponse {
	private String en;
	private String id;
}
