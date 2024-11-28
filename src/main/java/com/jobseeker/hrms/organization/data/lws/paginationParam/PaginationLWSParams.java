package com.jobseeker.hrms.organization.data.lws.paginationParam;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jobseeker.data.PaginationParam;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaginationLWSParams extends PaginationParam {

	private String areaId;

	private String branchId;

	private String divisionId;
}
