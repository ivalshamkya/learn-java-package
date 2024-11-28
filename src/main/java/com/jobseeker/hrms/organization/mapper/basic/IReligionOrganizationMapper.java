package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.religion.ReligionDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.GeneralMapperService;
import org.jobseeker.master.Religion;
import org.jobseeker.organization.ReligionOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    GeneralMapperService.class
})
public interface IReligionOrganizationMapper {
    
    @Mapping(source = "master.name", target = "name", qualifiedByName = "setMultiLangToObject")
    @Mapping(source = "master._id", target = "_id")
    ReligionDataResponse toReligionDataResponse(ReligionOrganization religion);
}
