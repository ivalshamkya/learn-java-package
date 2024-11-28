package com.jobseeker.hrms.organization.repositories.sms.vacancy;

import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaActiveVacancyLWSResponse;

import java.util.List;

public interface VacancyOrgSMSRepositoryAdd {
    List<WorkAreaActiveVacancyLWSResponse> findWorkAreaActiveVacancy();
}
