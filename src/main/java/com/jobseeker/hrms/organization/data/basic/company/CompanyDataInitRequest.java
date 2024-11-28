package com.jobseeker.hrms.organization.data.basic.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.master.PhoneDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDataInitRequest {

	@NotBlank
	@Size(min = 3)
	private String code;

	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	private String profile;

	@NotBlank
	private String industryTypeId;

	private String address;

	@NotBlank
	private String cityId;

	private String districtId;

	private String subDistrictId ;

	private PhoneDataEmbed phoneNumber;
}
