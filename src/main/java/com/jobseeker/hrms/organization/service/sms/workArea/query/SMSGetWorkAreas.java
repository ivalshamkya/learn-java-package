package com.jobseeker.hrms.organization.service.sms.workArea.query;

import com.jobseeker.hrms.organization.data.sms.workArea.WorkAreaSmsDataResponse;
import com.jobseeker.hrms.organization.mapper.sms.ISmsWorkAreaMapper;
import com.jobseeker.hrms.organization.repositories.sms.organization.workArea.WorkAreaOrgSmsRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("SMSGetWorkAreas")
public class SMSGetWorkAreas {

    private final WorkAreaOrgSmsRepository workAreaOrgSmsRepository;
    private final ISmsWorkAreaMapper workAreaSmsMapper;

    public Page<WorkAreaSmsDataResponse> execute(PaginationParam param) {
        return workAreaOrgSmsRepository.findByDataTables(param)
                .map(workAreaSmsMapper::toWorkAreaSmsDataResponse);
    }
}
