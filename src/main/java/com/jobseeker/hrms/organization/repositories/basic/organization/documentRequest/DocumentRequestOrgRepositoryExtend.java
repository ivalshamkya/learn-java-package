package com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest;

import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationBasicParams;
import org.jobseeker.organization.DocumentRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DocumentRequestOrgRepositoryExtend {

	Optional<DocumentRequest> findFirstByActive(String positionId);

	Page<DocumentRequest> findByDataTables(PaginationBasicParams param);
}
