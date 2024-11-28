package com.jobseeker.hrms.organization.service.basic.workplacement.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataRequest;
import com.jobseeker.hrms.organization.data.basic.workplacement.WorkplacementDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IWorkplacementMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.workplacement.WorkplacementOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.Workplacement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("updateWorkplacement")
public class UpdateWorkplacement {

    private final CompanyOrgRepository companyRepository;
    private final WorkplacementOrgOrgRepository workplacementOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IWorkplacementMapper workplacementMapper;

    public WorkplacementDataResponse execute(WorkplacementDataRequest request, String oid) {
        Workplacement workplacement = composeBranch(request, oid);

        return workplacementMapper.toWorkplacementDataResponse(workplacement);
    }

    private Workplacement composeBranch(WorkplacementDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        Workplacement workplacement = new Workplacement();
        if (oid != null) {
            workplacement = workplacementOrgRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_RECRUITMENT_STAGE_WITH_ID_FOUND.getMsg()));
            workplacement.setUpdatedAt(LocalDateTime.now());
        } else {
            workplacement.setStatus(StatusData.ACTIVE);
            workplacement.setCreatedAt(LocalDateTime.now());
        }

        workplacement.setCompany(companyMapper.toAttachCompany(company));

        workplacementMapper.toWriteWorkplacement(workplacement, request);
        return workplacementOrgRepository.save(workplacement);
    }
}
