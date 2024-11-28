package com.jobseeker.hrms.organization.mapper.sms;

import com.jobseeker.hrms.organization.data.basic.branch.BranchDataRequest;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataRequest;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.MasterMapperService;
import com.jobseeker.hrms.organization.mapper.basic.service.OrganizationMapperService;
import com.jobseeker.hrms.organization.mapper.sms.service.SmsOrganizationMapperService;
import org.jobseeker.organization.Branch;
import org.jobseeker.organization.sms.BranchSMS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MasterMapperService.class, SmsOrganizationMapperService.class})
public interface ISmsBranchMapper {
    BranchSmsDataResponse toBranchSmsDataResponse(BranchSMS branchSMS);

    @Mapping(source = "workAreaId", target = "workArea", qualifiedByName = "smsSetWorkArea")
    void toWriteBranchSMS(@MappingTarget BranchSMS branchSMS, BranchSmsDataRequest request);
}
