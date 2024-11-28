package com.jobseeker.hrms.organization.data.basic.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jobseeker.hrms.organization.config.serializer.DateTimezoneSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.master.IndustryTypeDataEmbed;
import org.jobseeker.embedded.master.PhoneDataEmbed;
import org.jobseeker.embedded.master.SosmedDataEmbed;
import org.jobseeker.embedded.mediafile.MediaDataEmbed;
import org.jobseeker.embedded.organization.CreditDataEmbed;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDataResponse {

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

//	private String careerSiteUrl;
	private String language;
	private String status;

	private MediaDataEmbed logo;
	private String logoPosition;
	private CreditDataEmbed credit;
	private PhoneDataEmbed phoneNumber;
	private IndustryTypeDataEmbed industryType;

	private String city;
	private String district;
	private String subDistrict;
	private List<SosmedDataEmbed> sosmed;

	private Long totalEmployee;
	@JsonSerialize(using = DateTimezoneSerializer.class)
	private Date expiryDate;
	private Integer remainingDays;
}