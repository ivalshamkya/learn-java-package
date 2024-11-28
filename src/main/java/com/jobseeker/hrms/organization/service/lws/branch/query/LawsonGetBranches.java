package com.jobseeker.hrms.organization.service.lws.branch.query;

import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IBranchLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.branch.BranchOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetBranches")
public class LawsonGetBranches {


    private final BranchOrgLWSRepository branchRepository;

    private final IBranchLWSMapper branchMapper;

    public Page<LawsonBranchDataResponse> execute(Map<Object, Object> param) {
        return branchRepository.findByDataTables(param)
                .map(branchMapper::toBranchDataResponse);
    }
}
