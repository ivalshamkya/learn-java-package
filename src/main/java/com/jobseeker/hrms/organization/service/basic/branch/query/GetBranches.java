package com.jobseeker.hrms.organization.service.basic.branch.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.branch.BranchOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getBranches")
public class GetBranches {

    private final BranchOrgRepository branchRepository;

    private final IBranchMapper branchMapper;

    public Page<Object> execute(Map<Object, Object> param) {
        if (ServletRequestAWS.getRemoteHost().contains("basic"))
            return branchRepository.findByDataTables(param)
                    .map(branchMapper::toBranchJLifeDataResponse);

        return branchRepository.findByDataTables(param)
                .map(branchMapper::toBranchDataResponse);
    }
}
