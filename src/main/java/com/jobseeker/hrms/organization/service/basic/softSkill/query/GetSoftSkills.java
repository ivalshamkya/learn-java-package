package com.jobseeker.hrms.organization.service.basic.softSkill.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ISoftSkillMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.softSkill.SoftSkillOrgOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.softSkill.SoftSkillOrgOrgRepositoryExtend;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.repositories.SoftSkillOrgRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getSoftSkills")
public class GetSoftSkills {
    
    private final SoftSkillOrgOrgRepository softSkillOrgRepository;
    private final ISoftSkillMapper softSkillMapper;
    
    public Page<SoftSkillDataResponse> execute(PaginationParam param) {
        return softSkillOrgRepository.findByDataTables(param)
            .map(softSkillMapper::toSoftSkillDataResponse);
    }
}
