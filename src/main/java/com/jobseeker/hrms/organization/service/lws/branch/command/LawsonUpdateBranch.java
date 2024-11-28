package com.jobseeker.hrms.organization.service.lws.branch.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataRequest;
import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IBranchLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.branch.BranchOrgLWSRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.BranchLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonUpdateBranch")
public class LawsonUpdateBranch {

    private final CompanyOrgRepository companyRepository;
    private final BranchOrgLWSRepository branchRepository;

    private final ICompanyMapper companyMapper;
    private final IBranchLWSMapper branchMapper;
    private final Validator validator;

    public LawsonBranchDataResponse execute(LawsonBranchDataRequest request, String oid) {
        BranchLawson branch = composeBranch(request, oid);

        return branchMapper.toBranchDataResponse(branch);
    }

    private BranchLawson composeBranch(LawsonBranchDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        BranchLawson branch = new BranchLawson();
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
