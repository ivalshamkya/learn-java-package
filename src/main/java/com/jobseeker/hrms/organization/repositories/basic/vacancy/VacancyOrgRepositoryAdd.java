package com.jobseeker.hrms.organization.repositories.basic.vacancy;

import com.jobseeker.hrms.organization.data.basic.filter.response.CityActiveVacancyResponse;
import com.jobseeker.hrms.organization.data.basic.filter.response.DepartmentActiveVacancyResponse;

import java.util.List;

public interface VacancyOrgRepositoryAdd {

    List<DepartmentActiveVacancyResponse> findDepartmentActiveVacancy();

    List<CityActiveVacancyResponse> findCityActiveVacancy();

}
