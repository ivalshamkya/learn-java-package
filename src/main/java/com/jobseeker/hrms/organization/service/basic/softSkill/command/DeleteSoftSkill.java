package com.jobseeker.hrms.organization.service.basic.softSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
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
@Qualifier("deleteSoftSkill")
public class DeleteSoftSkill {
    private final CompanyOrgRepository companyOrgRepository;
    private final SoftSkillOrgRepository softSkillOrgRepository;
    
    public String execute(String oid) {
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
        SoftSkillOrganization softSkill = softSkillOrgRepository.findByCompanyAndId(company.get_id(), oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        softSkill.setDeletedAt(LocalDateTime.now());
        softSkill.setStatus(StatusData.DELETED);
        softSkillOrgRepository.save(softSkill);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
