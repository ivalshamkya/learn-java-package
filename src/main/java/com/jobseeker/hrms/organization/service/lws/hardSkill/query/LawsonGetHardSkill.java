package com.jobseeker.hrms.organization.service.lws.hardSkill.query;

import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IHardSkillLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill.HardSkillOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.HardSkillOrganization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetHardSkill")
public class LawsonGetHardSkill {
    
    private final HardSkillOrgOrgRepository hardSkillOrgRepository;
    private final IHardSkillLWSMapper hardSkillMapper;

    public HardSkillLawsonDataResponse execute(String oid){
        HardSkillOrganization hardSkill = hardSkillOrgRepository.findFirstByActive(oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        return hardSkillMapper.toLawsonHardSkillDataResponse(hardSkill);
    }
}
