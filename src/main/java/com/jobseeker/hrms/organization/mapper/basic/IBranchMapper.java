package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.branch.BranchDataRequest;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.data.basic.branch.BranchJLifeDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.BranchMapperService;
import com.jobseeker.hrms.organization.mapper.basic.service.MasterMapperService;
import com.jobseeker.hrms.organization.mapper.basic.service.OrganizationMapperService;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.sms.BranchSMS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        MasterMapperService.class, OrganizationMapperService.class,
        BranchMapperService.class,
})
public interface IBranchMapper {

    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "pic.name", target = "pic")
    @Mapping(source = "_id", target = "totalEmployee", qualifiedByName = "setTotalEmployee")
    @Mapping(source = "_id", target = "totalVacancy", qualifiedByName = "setTotalVacancy")
    BranchDataResponse toBranchDataResponse(Branch branch);

    @Mapping(source = "cityId", target = "city", qualifiedByName = "setCity")
    @Mapping(source = "districtId", target = "district", qualifiedByName = "setDistrict")
    @Mapping(source = "subDistrictId", target = "subDistrict", qualifiedByName = "setSubDistrict")
    @Mapping(source = "longlat", target = "coordinate", qualifiedByName = "setCoordinate")
    void toWriteBranch(@MappingTarget Branch branch, BranchDataRequest request);

    BranchSmsDataResponse toBranchSmsDataResponse(BranchSMS branchSMS);

    //<editor-fold desc="temporary support for basic version">
    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "pic.name", target = "pic")
    @Mapping(target = "status", qualifiedByName = "setStatusBasic")
    @Mapping(source = "_id", target = "totalEmployee", qualifiedByName = "setTotalEmployee")
    @Mapping(source = "_id", target = "totalVacancy", qualifiedByName = "setTotalVacancy")
    BranchJLifeDataResponse toBranchJLifeDataResponse(Branch branch);

    @Named("setStatusBasic")
    default Integer setStatusBasic(StatusData status) {
        if (status == null) return null;
        return status.equals(StatusData.ACTIVE) ? 1 : 0;
    }
    //</editor-fold>

}
