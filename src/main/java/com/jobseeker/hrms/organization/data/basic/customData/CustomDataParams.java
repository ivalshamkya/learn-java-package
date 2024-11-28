package com.jobseeker.hrms.organization.data.basic.customData;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomDataParams extends PaginationParams {
	@NotBlank
	private String groupCode;
}
