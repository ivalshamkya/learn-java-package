package com.jobseeker.hrms.organization.service;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.jobseeker.enums.datatable.SortField;
import org.springframework.data.domain.*;
public abstract class BasePaginationService<T> {

    protected Page<T> paginate(PaginationParams params, PaginatedDataFetcher<T> dataFetcher) {
        Pageable pageable;
        int page;

        if (params.getPage() == 0) {
            pageable = PageRequest.of(
                    0, Integer.MAX_VALUE,
                    Sort.Direction.ASC, SortField.name.getDatabaseFieldName()
            );
        } else {
            page = params.getPage() - 1;
            pageable = PageRequest.of(
                    page, params.getLimit(),
                    Sort.Direction.ASC, SortField.name.getDatabaseFieldName()
            );
        }

        Page<T> data = dataFetcher.fetchData(pageable);

        long totalData = data.getTotalElements() != 0 ? data.getTotalElements() : data.getContent().size();
        return new PageImpl<>(data.getContent(), pageable, totalData);
    }

    @FunctionalInterface
    public interface PaginatedDataFetcher<T> {
        Page<T> fetchData(Pageable pageable);
    }
}
