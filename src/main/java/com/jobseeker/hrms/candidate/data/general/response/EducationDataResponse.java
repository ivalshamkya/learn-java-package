package com.jobseeker.hrms.candidate.data.general.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jobseeker.hrms.candidate.config.serializer.DateTimezoneSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jobseeker.embedded.general.GeneralDataEmbed;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDataResponse {

	private GeneralDataEmbed degree;

	private GeneralDataEmbed major;

	private double gpa;

	@JsonSerialize(using = DateTimezoneSerializer.class)
	private Date startDate;

	@JsonSerialize(using = DateTimezoneSerializer.class)
	private Date graduateDate;

	private boolean isUntilNow = false;

	private int pointEducation;

	private String logo;

	private GeneralDataEmbed institution;

	private String certificate;

	private GeneralDataEmbed province;

	private GeneralDataEmbed city;

	private GeneralDataEmbed district;

	private GeneralDataEmbed subDistrict;

}