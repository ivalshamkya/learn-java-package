package com.jobseeker.hrms.organization.service.lws.hardSkill.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
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
@Qualifier("lawsonDeleteHardSkill")
public class LawsonDeleteHardSkill {
    
    private final CompanyOrgRepository companyOrgRepository;
    private final HardSkillOrgRepository hardSkillOrgRepository;
    private final VacancyOrgLWSRepository vacancyRepository;
    
    /**
     *
     * Check company id, check data hard skill and last delete
     */
    public String execute(String oid) {
        Company company = companyOrgRepository.findById(ServletRequestAWS.getCompanyId())
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));
        HardSkillOrganization hardSkill = hardSkillOrgRepository.findByCompanyAndId(company.get_id(), oid)
            .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DOCUMENT_REQUEST_WITH_ID_FOUND.getMsg()));
        hardSkill.setStatus(StatusData.DELETED);
        hardSkill.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.existsByHardSkillId(oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete hard skill because it's used in vacancy data.");
        }
        //</editor-fold>

        hardSkillOrgRepository.save(hardSkill);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
