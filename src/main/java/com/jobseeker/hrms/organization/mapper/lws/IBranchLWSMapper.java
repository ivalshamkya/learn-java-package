package com.jobseeker.hrms.organization.mapper.lws;

import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataRequest;
import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.MasterMapperService;
import com.jobseeker.hrms.organization.mapper.basic.service.OrganizationMapperService;
import org.jobseeker.organization.lawson.BranchLawson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MasterMapperService.class, OrganizationMapperService.class})
public interface IBranchLWSMapper {

    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "pic.name", target = "pic")
    LawsonBranchDataResponse toBranchDataResponse(BranchLawson branch);

    @Mapping(source = "cityId", target = "city", qualifiedByName = "setCity")
    @Mapping(source = "longlat", target = "coordinate", qualifiedByName = "setCoordinate")
    void toWriteBranch(@MappingTarget BranchLawson branch, LawsonBranchDataRequest request);
}
