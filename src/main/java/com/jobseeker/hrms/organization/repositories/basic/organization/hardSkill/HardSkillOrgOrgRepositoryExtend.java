package com.jobseeker.hrms.organization.repositories.basic.organization.hardSkill;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.HardSkillOrganization;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface HardSkillOrgOrgRepositoryExtend {
    
    Page<HardSkillOrganization> findByDataTables(PaginationParam param);
    
    Optional<HardSkillOrganization> findFirstByActive(String hardSkillId);
}
