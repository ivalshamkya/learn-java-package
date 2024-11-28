package com.jobseeker.hrms.organization.service.basic.department.command;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.basic.employee.EmployeeOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.department.DepartmentOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("deleteDepartmentService")
public class DeleteDepartmentService {
	private final DepartmentOrgRepository departmentRepository;
	private final VacancyOrgRepository vacancyRepository;
	private final EmployeeOrgRepository employeeRepository;

	public String execute(String oid) {
		Department department = departmentRepository.findFirstByActive(oid)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));
		department.setStatus(StatusData.DELETED);
		department.setDeletedAt(LocalDateTime.now());

		//<editor-fold desc="Check if used in Vacancy">?
		boolean existInVacancy = vacancyRepository.isExist("department._id", oid);
		if (existInVacancy) {
			throw new NoSuchElementException("Can't delete department because it's used in vacancy data.");
		}
		//</editor-fold>

		//<editor-fold desc="Check if used in Employee">
		boolean existInEmployee = employeeRepository.isExist("employment.department._id", oid);
		if (existInEmployee) {
			throw new NoSuchElementException("Can't delete department because it's used in employee data.");
		}
		//</editor-fold>

		departmentRepository.save(department);
		return MessageResponse.MSG_SUCCESS_DELETE_DATA.getMessage();
	}
}
