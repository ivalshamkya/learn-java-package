package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.maritalStatus.MaritalStatusDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.GeneralMapperService;
import org.jobseeker.organization.MaritalStatusOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        GeneralMapperService.class

})
public interface IMaritalStatusMapper {
    @Mapping(source = "master.name", target = "name", qualifiedByName = "setMultiLangToObject")
    @Mapping(source = "master._id", target = "_id")
    MaritalStatusDataResponse toLawsonSATMaritalStatus(MaritalStatusOrganization data);
}
