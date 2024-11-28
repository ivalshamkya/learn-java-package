package com.jobseeker.hrms.organization.data.basic.company;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDataSimpleRequest {

	private String address;

	private String greetingMsg;

	private String nppNumber;

	private String npwpNumber;

	private String logoUrl;

	private String logoPosition;

	@Pattern(regexp = "^(Asia/Jakarta|Asia/Makassar|Asia/Jayapura)$",
			message = "Field allowed input: Asia/Jakarta or Asia/Makassar or Asia/Jayapura")
	private String timezone;
}
