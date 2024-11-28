package com.jobseeker.hrms.organization.service.basic.recruitmentStage.query;

import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IRecruitmentStageMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage.RecruitmentStageOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getRecruitmentStages")
public class GetRecruitmentStages {

    private final RecruitmentStageOrgRepository recruitmentStageOrgRepository;

    private final IRecruitmentStageMapper recruitmentStageMapper;

    public Page<RecruitmentStageDataResponse> execute(PaginationParam param) {
        return recruitmentStageOrgRepository.findByDataTables(param)
                .map(recruitmentStageMapper::toRecruitmentStageDataResponse);
    }
}
