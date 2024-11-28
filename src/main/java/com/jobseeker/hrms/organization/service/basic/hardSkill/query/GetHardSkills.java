package com.jobseeker.hrms.organization.service.basic.hardSkill.query;

import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IHardSkillMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill.HardSkillOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getHardSkills")
public class GetHardSkills {
    
    private final HardSkillOrgOrgRepository hardSkillOrgRepository;
    private final IHardSkillMapper hardSkillMapper;
    
    public Page<HardSkillDataResponse> execute(PaginationParam param) {

        return hardSkillOrgRepository.findByDataTables(param)
            .map(hardSkillMapper::toHardSkillDataResponse);
    }
}
