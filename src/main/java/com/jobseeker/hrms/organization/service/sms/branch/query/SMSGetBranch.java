package com.jobseeker.hrms.organization.service.sms.branch.query;

import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.sms.organization.branch.BranchSMSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.sms.BranchSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getBranch")
public class SMSGetBranch {

    private final BranchSMSRepository branchRepository;

    private final IBranchMapper branchMapper;

    public BranchSmsDataResponse execute(String oid) {
        BranchSMS branchSMS = branchRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
        return branchMapper.toBranchSmsDataResponse(branchSMS);
    }
}
