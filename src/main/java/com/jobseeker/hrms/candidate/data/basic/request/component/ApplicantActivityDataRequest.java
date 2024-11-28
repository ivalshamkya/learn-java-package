package com.jobseeker.hrms.candidate.data.basic.request.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.enums.vacancy.applicant.ApplyActivityType;
import org.jobseeker.enums.vacancy.applicant.ApplyStatusType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantActivityDataRequest {

	@JsonProperty("oid")
	private String _id;

	private String applyProcess;

	private ApplyStatusType status;

	private ApplyActivityType type;

	private String from;

	private Boolean inReview;

	private String notes;

	private EmployeeDataEmbed actionBy;

//	@JsonSerialize(using = TimezoneSerializer.class)
	private LocalDateTime actionAt;

//	@JsonSerialize(using = TimezoneSerializer.class)
	private LocalDateTime expiredAt;

//	@JsonSerialize(using = TimezoneSerializer.class)
	private LocalDateTime cvDate;
}
