package com.jobseeker.hrms.organization.service.lws.branch.query;

import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IBranchLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.branch.BranchOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.lawson.BranchLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetBranch")
public class LawsonGetBranch {

    private final BranchOrgLWSRepository branchRepository;

    private final IBranchLWSMapper branchMapper;

    public LawsonBranchDataResponse execute(String oid) {
        BranchLawson branch = branchRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_BRANCH_WITH_ID_FOUND.getMsg()));
        return branchMapper.toBranchDataResponse(branch);
    }
}
