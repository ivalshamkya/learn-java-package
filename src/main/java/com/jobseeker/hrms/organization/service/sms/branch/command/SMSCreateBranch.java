package com.jobseeker.hrms.organization.service.sms.branch.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataRequest;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataRequest;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.sms.ISmsBranchMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.sms.organization.branch.BranchSMSRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.sms.BranchSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("SMSCreateBranch")
public class SMSCreateBranch {

    private final CompanyOrgRepository companyRepository;
    private final BranchSMSRepository branchRepository;

    private final ICompanyMapper companyMapper;
    private final ISmsBranchMapper branchMapper;

    public BranchSmsDataResponse execute(BranchSmsDataRequest request) {
        BranchSMS branchSMS = composeBranch(request, null);

        return branchMapper.toBranchSmsDataResponse(branchSMS);
    }

    private BranchSMS composeBranch(BranchSmsDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        BranchSMS branchSMS = new BranchSMS();
        if (oid != null) {
            branchSMS = branchRepository.findById(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
            branchSMS.setUpdatedAt(LocalDateTime.now());
        } else {
            branchSMS.setStatus(StatusData.ACTIVE);
            branchSMS.setCreatedAt(LocalDateTime.now());
        }

        branchSMS.setCompany(companyMapper.toAttachCompany(company));

        branchMapper.toWriteBranchSMS(branchSMS, request);
        return branchRepository.save(branchSMS);
    }
}
