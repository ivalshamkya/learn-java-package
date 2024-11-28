package com.jobseeker.hrms.organization.data.basic.approval;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDataRequest {

	@NotNull
	private List<String> employeeCodes;

	@NotBlank
	@Pattern(regexp = "^(VACANCY|REIMBURSEMENT)$",
			message = "Field allowed input: VACANCY or REIMBURSEMENT")
	private String type;
}
