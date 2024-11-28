package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataRequest;
import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataResponse;
import org.jobseeker.organization.HardSkillOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IHardSkillLWSMapper {
    HardSkillLawsonDataResponse toLawsonHardSkillDataResponse(HardSkillOrganization hardSkill);
    
    void toWriteHardSkill(@MappingTarget HardSkillOrganization hardSkill, HardSkillLawsonDataRequest request);
}
