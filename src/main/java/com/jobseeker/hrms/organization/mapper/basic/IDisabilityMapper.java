package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.disability.DisabilityDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.GeneralMapperService;
import org.jobseeker.organization.DisabilityOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        GeneralMapperService.class

})
public interface IDisabilityMapper {
    @Mapping(source = "master.name", target = "name", qualifiedByName = "setMultiLangToObject")
    @Mapping(source = "master._id", target = "_id")
    DisabilityDataResponse toLawsonSATDisability(DisabilityOrganization data);
}
