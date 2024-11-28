package com.jobseeker.hrms.organization.data.sms.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jobseeker.data.PaginationParam;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaginationBranchSmsParams extends PaginationParam {
//	@NotBlank
	private String workAreaIds;
}
