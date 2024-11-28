package com.jobseeker.hrms.candidate.data.general.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jobseeker.hrms.candidate.config.serializer.DateTimezoneSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityTypeDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String number;

	private String file;

	@JsonSerialize(using = DateTimezoneSerializer.class)
	private Date expiredDate;

}