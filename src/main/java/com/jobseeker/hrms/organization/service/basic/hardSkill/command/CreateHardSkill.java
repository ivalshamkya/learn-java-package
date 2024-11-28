package com.jobseeker.hrms.organization.service.basic.hardSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataRequest;
import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
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
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createHardSkill")
public class CreateHardSkill {
    
    private final CompanyOrgRepository companyOrgRepository;
    private final HardSkillOrgRepository hardSkillRepository;
    
    private final IHardSkillMapper hardSkillMapper;
    private final ICompanyMapper companyMapper;
    
    public HardSkillDataResponse execute(HardSkillDataRequest request){
        HardSkillOrganization response = composeBranch(request);
        return hardSkillMapper.toHardSkillDataResponse(response);
    }
    
    private HardSkillOrganization composeBranch(HardSkillDataRequest request){
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        HardSkillOrganization hardSkill = new HardSkillOrganization();
        hardSkillMapper.toWriteHardSkill(hardSkill, request);
        hardSkill.setCreatedAt(LocalDateTime.now());
        hardSkill.setStatus(StatusData.ACTIVE);
        hardSkill.setCompany(companyMapper.toAttachCompany(company));

        return hardSkillRepository.save(hardSkill);
    }
}
