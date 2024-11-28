package com.jobseeker.hrms.organization.service.lws.workArea.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.workArea.WorkAreaOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.lawson.WorkAreaLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonDeleteWorkAreaService")
public class LawsonDeleteWorkAreaService {
	private final WorkAreaOrgLWSRepository repository;
	private final VacancyOrgLWSRepository vacancyRepository;
	private final EmployeeOrgLWSRepository employeeRepository;

	public String execute(String oid) {
		WorkAreaLawson data = repository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Work Area")));
		data.setStatus(StatusData.DELETED);
		data.setDeletedAt(LocalDateTime.now());

		//<editor-fold desc="Check if used in Vacancy">?
		boolean existInVacancy = vacancyRepository.existsByWorkAreaId(oid);
		if (existInVacancy) {
			throw new NoSuchElementException("Can't delete work area because it's used in vacancy data.");
		}
		//</editor-fold>

		//<editor-fold desc="Check if used in Employee">
		boolean existInEmployee = employeeRepository.existsByWorkAreaId(oid);
		if (existInEmployee) {
			throw new NoSuchElementException("Can't delete work area because it's used in employee data.");
		}
		//</editor-fold>

		repository.save(data);
		return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
	}
}
