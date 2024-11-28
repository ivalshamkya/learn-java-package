package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.reasonOrganization.ReasonOrganizationDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.GeneralMapperService;
import org.jobseeker.organization.ReasonOrganization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {
    GeneralMapperService.class
    
})
public interface IReasonOrganizationMapper {
    @Mapping(source = "name", target = "name", qualifiedByName = "setMultiLangToObject")
    ReasonOrganizationDataResponse toReasonDataResponse(ReasonOrganization data);
}
