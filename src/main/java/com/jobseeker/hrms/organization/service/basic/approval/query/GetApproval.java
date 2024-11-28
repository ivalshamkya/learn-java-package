package com.jobseeker.hrms.organization.service.basic.approval.query;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataResponse;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import com.jobseeker.hrms.organization.mapper.basic.IApprovalMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.approval.ApprovalOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Approval;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getApproval")
public class GetApproval {

    private final ApprovalOrgRepository approvalOrgRepository;

    private final IApprovalMapper approvalMapper;

    public ApprovalDataResponse execute(String oid) {
        Approval approval = approvalOrgRepository.findById(oid).orElseThrow(() -> new NoSuchElementException("Approval not found"));
        return approvalMapper.toApprovalDataResponse(approval);
    }
}
