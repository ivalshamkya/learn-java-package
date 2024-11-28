package com.jobseeker.hrms.organization.service.sms.branch.query;

import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.PaginationBranchSmsParams;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.sms.organization.branch.BranchSMSRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.helper.ObjectMapperHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getBranches")
public class SMSGetBranches {

    private final BranchSMSRepository branchRepository;

    private final IBranchMapper branchMapper;

    public Page<BranchSmsDataResponse> execute(PaginationBranchSmsParams data) {
        return branchRepository.findByDataTables(data)
                .map(branchMapper::toBranchSmsDataResponse);
    }
}
