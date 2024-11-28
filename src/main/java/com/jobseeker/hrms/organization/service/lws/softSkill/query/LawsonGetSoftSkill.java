package com.jobseeker.hrms.organization.service.lws.softSkill.query;

import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ISoftSkillMapper;
import com.jobseeker.hrms.organization.mapper.lws.ISoftSkillLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.softSkill.SoftSkillOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.SoftSkillOrganization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetSoftSkill")
public class LawsonGetSoftSkill {
    
    private final SoftSkillOrgOrgRepository softSkillOrgRepository;
    private final ISoftSkillLWSMapper softSkillMapper;
    
    public SoftSkillLawsonDataResponse execute(String oid) {
        SoftSkillOrganization softSkill = softSkillOrgRepository.findFirstByActive(oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        return softSkillMapper.toLawsonSoftSkillDataResponse(softSkill);
    }
}
