package com.jobseeker.hrms.organization.repositories.basic.organization.softSkill;

import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.SoftSkillOrganization;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SoftSkillOrgOrgRepositoryExtend {
    Page<SoftSkillOrganization> findByDataTables(PaginationParam param);
    
    Optional<SoftSkillOrganization> findFirstByActive(String softSkillId);
}
