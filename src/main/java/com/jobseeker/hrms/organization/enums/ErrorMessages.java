package com.jobseeker.hrms.organization.enums;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

	NO_COMPANY_WITH_ID("There are no companies with id " + ServletRequestAWS.getCompanyId()),
	NO_SOURCE_APP_FOUND("Source app not found"),

	NO_BRANCH_FOUND("Branch not found"),
	NO_BRANCH_WITH_ID_FOUND("Branch not found, seems to have been deleted"),

	NO_DEPARTMENT_FOUND("Department not found"),
	NO_DEPARTMENT_WITH_ID_FOUND("Department not found, seems to have been deleted"),

	NO_DIVISION_FOUND("Division not found"),
	NO_DIVISION_WITH_ID_FOUND("Division not found, seems to have been deleted"),

	NO_JOB_LEVEL_FOUND("Job Level not found"),
	NO_JOB_LEVEL_WITH_ID_FOUND("Job Level not found, seems to have been deleted"),

	NO_JOB_TYPE_FOUND("Job Leve / Employment Status not found"),
	NO_JOB_TYPE_WITH_ID_FOUND("Job Leve / Employment Status not found, seems to have been deleted"),

	NO_RECRUITMENT_STAGE_FOUND("Recruitment Stage Request not found"),
	NO_RECRUITMENT_STAGE_WITH_ID_FOUND("Recruitment Stage not found, seems to have been deleted"),

	NO_DOCUMENT_REQUEST_FOUND("Document Request not found"),
	NO_DOCUMENT_REQUEST_WITH_ID_FOUND("Document Request not found, seems to have been deleted"),

	NO_OFFERING_LETTER_FOUND("Offering Letter Request not found"),
	NO_OFFERING_LETTER_WITH_ID_FOUND("Offering Letter not found, seems to have been deleted"),

	NO_VACANCY_TEMPLATE_FOUND("Job Template not found"),
	NO_VACANCY_TEMPLATE_WITH_ID_FOUND("Job Template not found, seems to have been deleted"),

	NO_CITY_FOUND("City not found"),
	NO_DISTRICT_FOUND("District not found"),
	NO_SUB_DISTRICT_FOUND("Sub District not found"),
	NO_INDUSTRY_TYPE_FOUND("Industry Type not found"),

	NO_WORK_AREA_FOUND("Work Area not found"),
	NO_PKWT_TEMPLATE_FOUND("PKWT Template not found"),

	NO_EMPLOYEE_PROVIDED("No Employee provided")
	;

	private final String msg;
}
