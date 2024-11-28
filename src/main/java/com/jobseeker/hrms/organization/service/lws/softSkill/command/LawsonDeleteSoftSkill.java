package com.jobseeker.hrms.organization.service.lws.softSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
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
@Qualifier("lawsonDeleteSoftSkill")
public class LawsonDeleteSoftSkill {
    
    private final CompanyOrgRepository companyOrgRepository;
    private final SoftSkillOrgRepository softSkillOrgRepository;
    private final VacancyOrgLWSRepository vacancyRepository;
    
    public String execute(String oid) {
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
        SoftSkillOrganization softSkill = softSkillOrgRepository.findByCompanyAndId(company.get_id(), oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        softSkill.setDeletedAt(LocalDateTime.now());
        softSkill.setStatus(StatusData.DELETED);

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.existsBySoftSkillId(oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete soft skill because it's used in vacancy data.");
        }
        //</editor-fold>

        softSkillOrgRepository.save(softSkill);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
