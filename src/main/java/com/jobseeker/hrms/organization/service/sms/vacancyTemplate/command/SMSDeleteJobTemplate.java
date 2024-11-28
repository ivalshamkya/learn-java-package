package com.jobseeker.hrms.organization.service.sms.vacancyTemplate.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter.OfferingLetterOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.vacancyTemplate.sms.VacancyTemplateSMSRepository;
import com.jobseeker.hrms.organization.repositories.sms.vacancy.ApplicantOrgSMSRepository;
import com.jobseeker.hrms.organization.repositories.sms.vacancy.VacancyOrgSMSRepository;
import com.jobseeker.hrms.organization.repositories.sms.vacancy.VacancyOrgSMSRepositoryAdd;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.sms.VacancyTemplateSMS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("updateJobTemplate")
public class SMSDeleteJobTemplate {

    private final VacancyTemplateSMSRepository vacancyTemplateSMSRepository;
    private final VacancyOrgSMSRepository vacancyRepository;

    public String execute(String oid) {
        VacancyTemplateSMS vacancyTemplateSMS = vacancyTemplateSMSRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_VACANCY_TEMPLATE_WITH_ID_FOUND.getMsg()));
        vacancyTemplateSMS.setStatus(StatusData.DELETED);
        vacancyTemplateSMS.setDeletedAt(LocalDateTime.now());

        //<editor-fold desc="Check if used in Vacancy">
        boolean existInVacancy = vacancyRepository.isExist("vacancyTemplate._id", oid);
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete job template because it's used in vacancy data.");
        }
        //</editor-fold>

        vacancyTemplateSMSRepository.save(vacancyTemplateSMS);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
