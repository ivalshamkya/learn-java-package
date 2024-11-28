package com.jobseeker.hrms.organization.service.lws.recruitmentStage.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage.RecruitmentStageOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.RecruitmentStage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("deleteRecruitmentStage")
public class LawsonDeleteRecruitmentStage {

    private final RecruitmentStageOrgRepository recruitmentStageOrgRepository;

    public String execute(String oid) {
        RecruitmentStage recruitmentStage = recruitmentStageOrgRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_RECRUITMENT_STAGE_WITH_ID_FOUND.getMsg()));
        recruitmentStage.setStatus(StatusData.DELETED);
        recruitmentStage.setDeletedAt(LocalDateTime.now());

        recruitmentStageOrgRepository.save(recruitmentStage);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
