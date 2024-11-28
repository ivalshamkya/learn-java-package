package com.jobseeker.hrms.organization.service.basic.hardSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataRequest;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IHardSkillMapper;
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
@Qualifier("updateHardSkill")
public class UpdateHardSkill {
    
    private final CompanyOrgRepository companyOrgRepository;
    private final HardSkillOrgRepository hardSkillOrgRepository;
    
    private final IHardSkillMapper hardSkillMapper;
    
    
    public HardSkillDataResponse execute(HardSkillDataRequest request, String oid){
        HardSkillOrganization response = composeBranch(request, oid);
        return hardSkillMapper.toHardSkillDataResponse(response);
    }
    
    private HardSkillOrganization composeBranch(HardSkillDataRequest request, String oid){
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
