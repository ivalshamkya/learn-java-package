package com.jobseeker.hrms.organization.data.basic.paginationParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.enums.datatable.SortField;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationParams {
    private int page = 1;
    private int limit = 20;

    private SortField sortedField = SortField.createdAt;
    private Sort.Direction sortDirection = Sort.Direction.DESC;

    private String q = "";
}