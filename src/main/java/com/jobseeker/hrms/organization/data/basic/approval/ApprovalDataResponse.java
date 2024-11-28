package com.jobseeker.hrms.organization.data.basic.approval;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDataResponse {

	@JsonProperty("oid")
	private String _id;

	private EmployeeDataEmbed employee;

	private String position;

	private String department;

	private String branch;
}
