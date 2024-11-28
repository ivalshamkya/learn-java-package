package com.jobseeker.hrms.organization.service.basic.branch.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Branch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getBranch")
public class GetBranch {

    private final BranchOrgRepository branchRepository;

    private final IBranchMapper branchMapper;

    public Object execute(String oid) {
        Branch branch = branchRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));

        if (ServletRequestAWS.getRemoteHost().contains("basic"))
            return branchMapper.toBranchJLifeDataResponse(branch);

        return branchMapper.toBranchDataResponse(branch);
    }
}
