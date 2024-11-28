package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.houseStatus.HouseStatusDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.GeneralMapperService;
import org.jobseeker.organization.HouseStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        GeneralMapperService.class
})
public interface IHouseStatusMapper {
    @Mapping(source = "name", target = "name", qualifiedByName = "setMultiLangToObject")
    HouseStatusDataResponse toHouseStatusDataResponse(HouseStatus houseStatus);
}
