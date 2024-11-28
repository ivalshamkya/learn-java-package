package com.jobseeker.hrms.organization.service.lws.division.command;

import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IDivisionLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.division.DivisionLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("LawsonDeleteDivision")
public class LawsonDeleteDivision {

    private final DivisionLWSRepository divisionRepository;
    private final VacancyOrgLWSRepository vacancyRepository;
    private final EmployeeOrgLWSRepository employeeRepository;

    public String execute(String oid) {
        DivisionLawson divisionLawson = divisionRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DIVISION_WITH_ID_FOUND.getMsg()));
        divisionLawson.setStatus(StatusData.DELETED);
        divisionLawson.setDeletedAt(LocalDateTime.now());


        //<editor-fold desc="Check if used in Vacancy">?
        boolean existInVacancy = vacancyRepository.existsByDivisionId(new ObjectId(oid));
        if (existInVacancy) {
            throw new NoSuchElementException("Can't delete division because it's used in vacancy data.");
        }
        //</editor-fold>

        //<editor-fold desc="Check if used in Employee">
        boolean existInEmployee = employeeRepository.existsByDivisionId(oid);
        if (existInEmployee) {
            throw new NoSuchElementException("Can't delete division because it's used in employee data.");
        }
        //</editor-fold>

        divisionRepository.save(divisionLawson);
        return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
    }
}
