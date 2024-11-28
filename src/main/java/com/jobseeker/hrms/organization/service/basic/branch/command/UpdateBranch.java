package com.jobseeker.hrms.organization.service.basic.branch.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataRequest;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createBranch")
public class UpdateBranch {

    private final CompanyOrgRepository companyRepository;
    private final BranchOrgRepository branchRepository;

    private final ICompanyMapper companyMapper;
    private final IBranchMapper branchMapper;
    private final Validator validator;

    public BranchDataResponse execute(BranchDataRequest request, String oid) {
        Branch branch = composeBranch(request, oid);

        return branchMapper.toBranchDataResponse(branch);
    }

    private Branch composeBranch(BranchDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        Branch branch = new Branch();
        if (oid != null) {
            branch = branchRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
            branch.setUpdatedAt(LocalDateTime.now());
        } else {
            branch.setStatus(StatusData.ACTIVE);
            branch.setCreatedAt(LocalDateTime.now());
        }

        branch.setCompany(companyMapper.toAttachCompany(company));

        branchMapper.toWriteBranch(branch, request);
        return branchRepository.save(branch);
    }
}
