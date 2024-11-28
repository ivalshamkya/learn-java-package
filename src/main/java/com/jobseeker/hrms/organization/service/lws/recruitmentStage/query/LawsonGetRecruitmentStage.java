package com.jobseeker.hrms.organization.service.lws.recruitmentStage.query;

import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IRecruitmentStageLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage.RecruitmentStageOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.RecruitmentStage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getRecruitmentStage")
public class LawsonGetRecruitmentStage {

    private final RecruitmentStageOrgRepository recruitmentStageOrgRepository;
    private final IRecruitmentStageLWSMapper recruitmentStageMapper;

    public RecruitmentStageDataResponse execute(String oid) {
        RecruitmentStage recruitmentStage = recruitmentStageOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_RECRUITMENT_STAGE_WITH_ID_FOUND.getMsg()));
        return recruitmentStageMapper.toRecruitmentStageDataResponse(recruitmentStage);
    }
}
