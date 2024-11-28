package com.jobseeker.hrms.organization.service.basic.approval.query;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataResponse;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import com.jobseeker.hrms.organization.mapper.basic.IApprovalMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.approval.ApprovalOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getApprovals")
public class GetApprovals {

    private final ApprovalOrgRepository approvalOrgRepository;

    private final IApprovalMapper approvalMapper;

    public Page<ApprovalDataResponse> execute(ApprovalDatatableDataRequest param) {
        return approvalOrgRepository.findByDataTables(param)
                .map(approvalMapper::toApprovalDataResponse);
    }
}
