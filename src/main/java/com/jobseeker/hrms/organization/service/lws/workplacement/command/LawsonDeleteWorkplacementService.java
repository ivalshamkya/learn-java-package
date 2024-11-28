package com.jobseeker.hrms.organization.service.lws.workplacement.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.workplacement.WorkplacementOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ResponseGeneratorHelper;
import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonDeleteWorkplacementService")
public class LawsonDeleteWorkplacementService {
	private final WorkplacementOrgLWSRepository repository;
	private final VacancyOrgLWSRepository vacancyRepository;
	private final EmployeeOrgLWSRepository employeeRepository;

	public String execute(String oid) {
		WorkplacementLawson data = repository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ResponseGeneratorHelper.generateNotFoundMessage(ServletRequestAWS.getLanguage(), "Workplacement")));
		data.setStatus(StatusData.DELETED);
		data.setDeletedAt(LocalDateTime.now());

		//<editor-fold desc="Check if used in Vacancy">?
		boolean existInVacancy = vacancyRepository.existsByWorkPlacementId(oid);
		if (existInVacancy) {
			throw new NoSuchElementException("Can't delete work placement because it's used in vacancy data.");
		}
		//</editor-fold>

		//<editor-fold desc="Check if used in Employee">
		boolean existInEmployee = employeeRepository.existsByWorkPlacementId(oid);
		if (existInEmployee) {
			throw new NoSuchElementException("Can't delete work placement because it's used in employee data.");
		}
		//</editor-fold>

		repository.save(data);
		return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
	}
}
