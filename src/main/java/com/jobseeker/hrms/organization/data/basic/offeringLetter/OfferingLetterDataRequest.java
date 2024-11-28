package com.jobseeker.hrms.organization.data.basic.offeringLetter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferingLetterDataRequest {

	@NotBlank
	private String letterHead;

	private String greetings;

	@NotBlank
	private String body;

	@NotBlank
	private String additionalInformation;

	@NotBlank
	private String signeeRole;

	@NotBlank
	private String signeeName;

	private String signature;
}
