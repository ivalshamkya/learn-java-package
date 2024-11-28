package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.nationality.NationalityDataResponse;
import org.jobseeker.organization.Nationality;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface INationalityMapper {
    NationalityDataResponse toLawsonSATNationality(Nationality data);
}
