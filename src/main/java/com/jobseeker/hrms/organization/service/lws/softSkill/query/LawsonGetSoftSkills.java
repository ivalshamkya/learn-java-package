package com.jobseeker.hrms.organization.service.lws.softSkill.query;

import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.ISoftSkillMapper;
import com.jobseeker.hrms.organization.mapper.lws.ISoftSkillLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.softSkill.SoftSkillOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetSoftSkills")
public class LawsonGetSoftSkills {
    
    private final SoftSkillOrgOrgRepository softSkillOrgRepository;
    private final ISoftSkillLWSMapper softSkillMapper;
    
    public Page<SoftSkillLawsonDataResponse> execute(PaginationParam param) {
        return softSkillOrgRepository.findByDataTables(param)
            .map(softSkillMapper::toLawsonSoftSkillDataResponse);
    }
}
