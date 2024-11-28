package com.jobseeker.hrms.organization.service.basic.approval.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataRequest;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IApprovalMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.approval.ApprovalOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.employee.Employee;
import org.jobseeker.employee.repositories.EmployeeRepository;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.enums.organization.TypeApproval;
import org.jobseeker.helper.ObjectMapperHelper;
import org.jobseeker.organization.Approval;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("updateApproval")
public class UpdateApproval {

    public ApprovalDataResponse execute(Map<Object, Object> request, String oid) {
        return null;
    }
}
