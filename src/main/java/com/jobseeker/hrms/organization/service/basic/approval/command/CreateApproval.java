package com.jobseeker.hrms.organization.service.basic.approval.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataRequest;
import com.jobseeker.hrms.organization.mapper.basic.IApprovalMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.approval.ApprovalOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.employee.Employee;
import org.jobseeker.employee.repositories.EmployeeRepository;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.enums.organization.TypeApproval;
import org.jobseeker.organization.Approval;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createApproval")
public class CreateApproval {

    private final CompanyOrgRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ApprovalOrgRepository approvalOrgRepository;

    private final IApprovalMapper approvalMapper;

    @Transactional
    public Object execute(ApprovalDataRequest request) throws NoSuchFieldException {

        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId()).orElseThrow(() -> new NoSuchElementException("Company not found"));

        List<String> existingApprovalEmployeeIds = approvalOrgRepository.findByCompanyId(company.get_id(), request.getType()).orElse(new ArrayList<>())
                .stream()
                .map(data -> data.getEmployee().get_id())
                .toList();

        if (request.getEmployeeCodes() == null) {
            throw new NoSuchFieldException("employeeIds not provided");
        }

        // Add new approvals for employees in the request
        for (String employeeCode : request.getEmployeeCodes()) {
            if (!existingApprovalEmployeeIds.contains(employeeCode)) {
                System.out.println("New Data >>> " + employeeCode);
                Employee employee = employeeRepository.findById(employeeCode)
                        .orElseThrow(() -> new NoSuchElementException("No employee found"));

                EmployeeDataEmbed employeeDataEmbed = new EmployeeDataEmbed();
                employeeDataEmbed.set_id(employee.get_id());
                employeeDataEmbed.setName(employee.getName());
                employeeDataEmbed.setEmail(employee.getEmail());
                employeeDataEmbed.setEmployeeCode(employee.getEmployeeCode());

                Approval newApproval = approvalMapper.toSave(request);
                newApproval.setEmployee(employeeDataEmbed);
                newApproval.setCompany(approvalMapper.toAttachCompany(company));
                newApproval.setType(TypeApproval.valueOf(request.getType()));
                newApproval.setStatus(StatusData.ACTIVE);
                newApproval.setCreatedAt(LocalDateTime.now());

                approvalOrgRepository.save(newApproval);
            }
        }

        List<Approval> savedApprovals = approvalOrgRepository.findByCompanyId(company.get_id(), request.getType()).orElse(new ArrayList<>());

        for (Approval approval : savedApprovals) {
            if (!request.getEmployeeCodes().contains(approval.getEmployee().get_id())) {
                approvalOrgRepository.delete(approval);
            }
        }

        savedApprovals = approvalOrgRepository.findByCompanyId(company.get_id(), request.getType()).orElse(new ArrayList<>());

        return true;
    }
}
