package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataRequest;
import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataResponse;
import org.jobseeker.organization.SoftSkillOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ISoftSkillLWSMapper {
    SoftSkillLawsonDataResponse toLawsonSoftSkillDataResponse(SoftSkillOrganization hardSkill);
    
    void toWriteSoftSkill(@MappingTarget SoftSkillOrganization hardSkill, SoftSkillLawsonDataRequest request);
}
