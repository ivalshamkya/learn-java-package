package com.jobseeker.hrms.organization.data.basic.branch;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDataRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String address;

	@NotBlank
	private String cityId;

	private String districtId;

	private String subDistrictId;

	private String picId;

	@Min(value = 1)
	private int range;

	private String longlat;

}
