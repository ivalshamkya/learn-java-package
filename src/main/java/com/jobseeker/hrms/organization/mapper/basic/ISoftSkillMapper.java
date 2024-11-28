package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataRequest;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import org.jobseeker.organization.SoftSkillOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ISoftSkillMapper {
    
    SoftSkillDataResponse toSoftSkillDataResponse(SoftSkillOrganization softSkill);
    
    void toWriteSoftSkill(@MappingTarget SoftSkillOrganization hardSkill, SoftSkillDataRequest request);
}
