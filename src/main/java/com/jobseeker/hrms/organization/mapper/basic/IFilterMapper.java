package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.filter.ActiveVacancy;
import com.jobseeker.hrms.organization.data.basic.filter.response.CityActiveVacancyResponse;
import com.jobseeker.hrms.organization.data.basic.filter.response.DepartmentActiveVacancyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IFilterMapper {

    @Mapping(source = "department._id", target = "_id")
    @Mapping(source = "department.name", target = "name")
    DepartmentActiveVacancyResponse toDepartmentActiveVacancy(ActiveVacancy vacancy);

    @Mapping(source = "city._id", target = "_id")
    @Mapping(source = "city.name", target = "name")
    CityActiveVacancyResponse toCityActiveVacancy(ActiveVacancy vacancy);
}
