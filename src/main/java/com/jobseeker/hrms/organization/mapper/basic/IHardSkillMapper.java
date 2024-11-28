package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataRequest;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataResponse;
import org.jobseeker.organization.HardSkillOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IHardSkillMapper {
    
    HardSkillDataResponse toHardSkillDataResponse(HardSkillOrganization hardSkill);

    void toWriteHardSkill(@MappingTarget HardSkillOrganization hardSkill, HardSkillDataRequest request);
}
