package com.jobseeker.hrms.organization.repositories.lws.vacancy;

import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaActiveVacancyLWSResponse;

import java.util.List;

public interface VacancyOrgLWSRepositoryAdd {
    List<WorkAreaActiveVacancyLWSResponse> findWorkAreaActiveVacancy();
}
