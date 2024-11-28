package com.jobseeker.hrms.organization.service.lws.softSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataRequest;
import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataRequest;
import com.jobseeker.hrms.organization.data.lws.softSkill.SoftSkillLawsonDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.ISoftSkillMapper;
import com.jobseeker.hrms.organization.mapper.lws.ISoftSkillLWSMapper;
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
@Qualifier("lawsonCreateSoftSkill")
public class LawsonCreateSoftSkill {
    
    private final CompanyOrgRepository companyOrgRepository;
    private final SoftSkillOrgRepository softSkillOrgRepository;
    
    private final ISoftSkillLWSMapper softSkillMapper;
    private final ICompanyMapper companyMapper;
    
    public SoftSkillLawsonDataResponse execute(SoftSkillLawsonDataRequest request) {
        SoftSkillOrganization response = composeBranch(request);
        return softSkillMapper.toLawsonSoftSkillDataResponse(response);
    }
    
    private SoftSkillOrganization composeBranch(SoftSkillLawsonDataRequest request) {
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
        
        SoftSkillOrganization softSkill = new SoftSkillOrganization();
        softSkillMapper.toWriteSoftSkill(softSkill, request);
        softSkill.setCreatedAt(LocalDateTime.now());
        softSkill.setCompany(companyMapper.toAttachCompany(company));
        softSkill.setStatus(StatusData.ACTIVE);
        return softSkillOrgRepository.save(softSkill);
    }
}
