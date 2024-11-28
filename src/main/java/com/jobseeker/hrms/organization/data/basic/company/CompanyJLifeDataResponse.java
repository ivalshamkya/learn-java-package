package com.jobseeker.hrms.organization.data.basic.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jobseeker.hrms.organization.config.serializer.DateTimezoneSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.master.PhoneDataEmbed;
import org.jobseeker.embedded.master.SosmedDataEmbed;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyJLifeDataResponse {

	@JsonProperty("oid")
	private String _id;
	private String code;
	private String name;

	private String nppNumber;
	private String npwpNumber;
	private String greetingMsg;

	private String profile;
	private String address;
	private String email;

	private String language;
	private String status;

	private FileDataEmbedBasic logo;
	private String logoPosition;
	private String credit;
	private PhoneDataEmbed phoneNumber;
	private IndustryTypeDataEmbedBasic industryType;

	private String city;
	private String district;
	private String subDistrict;
	private List<SosmedDataEmbed> sosmed;

	private Long totalEmployee;
	@JsonSerialize(using = DateTimezoneSerializer.class)
	private Date expiryDate;
	private Integer remainingDays;

	@Data
	@NoArgsConstructor
	public static class FileDataEmbedBasic {
		@JsonProperty("file")
		private String link;
	}

	@Data
	@NoArgsConstructor
	public static class IndustryTypeDataEmbedBasic {
		@JsonProperty("oid")
		private String _id;
		private String name;
	}
}