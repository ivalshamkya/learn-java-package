package com.jobseeker.hrms.organization.data.sms.branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jobseeker.hrms.organization.config.timezone.ConvertToClientTimeZone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.organization.sms.WorkAreaSMS;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchSmsDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String code;

	private String name;

	private WorkAreaSMS workArea;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime updatedAt;
}