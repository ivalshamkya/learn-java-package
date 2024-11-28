package com.jobseeker.hrms.organization.service.basic.position.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.position.PositionOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Position;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonDeletePositionService")
public class DeletePositionService {
	private final PositionOrgRepository repository;
	private final EmployeeOrgRepository employeeRepository;

	public String execute(String oid) {
		Position position = repository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_WITH_ID_FOUND.getMsg()));
		position.setStatus(StatusData.DELETED);
		position.setDeletedAt(LocalDateTime.now());

		//<editor-fold desc="Check if used in Employee">
		boolean existInEmployee = employeeRepository.isExist("employment.position._id", oid);
		if (existInEmployee) {
			throw new NoSuchElementException("Can't delete position because it's used in employee data.");
		}
		//</editor-fold>

		repository.save(position);
		return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
	}
}
