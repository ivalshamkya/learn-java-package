package com.jobseeker.hrms.organization.data.basic.approval;

import lombok.Data;
import org.jobseeker.data.PaginationParam;

@Data
public class ApprovalDatatableDataRequest extends PaginationParam {
    private String type = "VACANCY";
}
