package com.jobseeker.hrms.organization.service.basic.softSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataRequest;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ISoftSkillMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.SoftSkillOrganization;
import org.jobseeker.organization.repositories.SoftSkillOrgRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("updateSoftSkill")
public class UpdateSoftSkill {
    private final CompanyOrgRepository companyOrgRepository;
    private final SoftSkillOrgRepository softSkillOrgRepository;
    
    private final ISoftSkillMapper softSkillMapper;
    
    public SoftSkillDataResponse execute(SoftSkillDataRequest request, String oid) {
        SoftSkillOrganization response = composeBranch(request, oid);
        return softSkillMapper.toSoftSkillDataResponse(response);
    }
    
    private SoftSkillOrganization composeBranch(SoftSkillDataRequest request, String id) {
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
        SoftSkillOrganization softSkill = softSkillOrgRepository.findByCompanyAndId(company.get_id(), id)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        softSkillMapper.toWriteSoftSkill(softSkill, request);
        softSkill.setUpdatedAt(LocalDateTime.now());
        softSkill.setStatus(StatusData.ACTIVE);
        return softSkillOrgRepository.save(softSkill);
    }
}
