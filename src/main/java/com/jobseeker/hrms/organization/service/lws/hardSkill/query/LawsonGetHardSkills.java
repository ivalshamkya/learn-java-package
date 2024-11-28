package com.jobseeker.hrms.organization.service.lws.hardSkill.query;

import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataResponse;
import com.jobseeker.hrms.organization.mapper.lws.IHardSkillLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill.HardSkillOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetHardSkills")
public class LawsonGetHardSkills {
    
    private final HardSkillOrgOrgRepository hardSkillOrgRepository;
    private final IHardSkillLWSMapper hardSkillMapper;
    
    public Page<HardSkillLawsonDataResponse> execute(PaginationParam param) {
        
        return hardSkillOrgRepository.findByDataTables(param)
            .map(hardSkillMapper::toLawsonHardSkillDataResponse);
    }
}
