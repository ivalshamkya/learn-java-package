package com.jobseeker.hrms.organization.service.lws.hardSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataRequest;
import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataRequest;
import com.jobseeker.hrms.organization.data.lws.hardSkill.HardSkillLawsonDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IHardSkillLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.HardSkillOrganization;
import org.jobseeker.organization.repositories.HardSkillOrgRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonUpdateHardSkill")
public class LawsonUpdateHardSkill {
    
    private final CompanyOrgRepository companyOrgRepository;
    private final HardSkillOrgRepository hardSkillOrgRepository;
    
    private final IHardSkillLWSMapper hardSkillMapper;
    
    
    public HardSkillLawsonDataResponse execute(HardSkillLawsonDataRequest request, String oid){
        HardSkillOrganization response = composeBranch(request, oid);
        return hardSkillMapper.toLawsonHardSkillDataResponse(response);
    }
    
    private HardSkillOrganization composeBranch(HardSkillLawsonDataRequest request, String oid){
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
        HardSkillOrganization hardSkill = hardSkillOrgRepository.findByCompanyAndId(company.get_id(), oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        hardSkillMapper.toWriteHardSkill(hardSkill, request);
        hardSkill.setUpdatedAt(LocalDateTime.now());
        hardSkill.setStatus(StatusData.ACTIVE);
        
        return hardSkillOrgRepository.save(hardSkill);
    }
}
