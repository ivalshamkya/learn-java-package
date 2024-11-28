package com.jobseeker.hrms.organization.service.basic.company.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("getCompany")
public class GetCompany {

    private final CompanyOrgRepository companyRepository;
    private final EmployeeOrgRepository employeeRepository;
    private final ICompanyMapper companyMapper;

    public Object execute() {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        Long totalEmployee = employeeRepository.countByCompany(company.get_id())
                .orElse(0L);

        if (ServletRequestAWS.getRemoteHost().contains("basic"))
            return companyMapper.toCompanyJLifeDataResponse(company);

        CompanyDataResponse dataResponse = companyMapper.toCompanyDataResponse(company);
        dataResponse.setTotalEmployee(totalEmployee);
        return dataResponse;
    }
}
