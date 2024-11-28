package com.jobseeker.hrms.organization.data.basic.branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchJLifeDataResponse {

	@JsonProperty("oid")
	private String _id;

	private String name;

	private String address;

	private String company;

	private GeneralDataEmbed city;

	private GeoJsonPoint coordinate;

	private String pic;

	private Integer range;

	private Integer status;

	private Integer totalEmployee;

	private Integer totalVacancy;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime updatedAt;

}
