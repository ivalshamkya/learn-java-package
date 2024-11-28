package com.jobseeker.hrms.organization.service.lws.department.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.organization.department.DepartmentOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.employee.EmployeeOrgLWSRepository;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonDeleteDepartmentService")
public class LawsonDeleteDepartmentService {
	private final DepartmentOrgRepository departmentRepository;
	private final VacancyOrgLWSRepository vacancyRepository;
	private final EmployeeOrgLWSRepository employeeRepository;

	public String execute(String oid) {
		Department department = departmentRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));
		department.setStatus(StatusData.DELETED);
		department.setDeletedAt(LocalDateTime.now());

		//<editor-fold desc="Check if used in Vacancy">?
		boolean existInVacancy = vacancyRepository.existsByDepartmentId(oid);
		if (existInVacancy) {
			throw new NoSuchElementException("Can't delete department because it's used in vacancy data.");
		}
		//</editor-fold>

		//<editor-fold desc="Check if used in Employee">
		boolean existInEmployee = employeeRepository.existsByDepartmentId(oid);
		if (existInEmployee) {
			throw new NoSuchElementException("Can't delete department because it's used in employee data.");
		}
		//</editor-fold>

		departmentRepository.save(department);
		return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
	}
}
