package com.jobseeker.hrms.organization.data.basic.jobType;

import jakarta.validation.constraints.Pattern;
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
public class PaginationJobTypeParam extends PaginationParam {

	@Pattern(regexp = "^(ACTIVE|INACTIVE|ALL)$",
			message = "Field allowed input: ACTIVE or INACTIVE or ALL")
	private String statusEmployment = "ACTIVE";

}
