package com.jobseeker.hrms.organization.service.basic.hardSkill.query;

import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IHardSkillMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill.HardSkillOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.HardSkillOrganization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getHardSkill")
public class GetHardSkill {
    
    private final HardSkillOrgOrgRepository hardSkillOrgRepository;
    private final IHardSkillMapper hardSkillMapper;
    
    public HardSkillDataResponse execute(String oid){
        HardSkillOrganization hardSkill = hardSkillOrgRepository.findFirstByActive(oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        return hardSkillMapper.toHardSkillDataResponse(hardSkill);
    }
}
