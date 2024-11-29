package com.jobseeker.hrms.candidate.mappers;

import org.jobseeker.embedded.area.CityDataEmbed;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.master.GenderDataEmbed;
import org.jobseeker.embedded.master.IndustryTypeDataEmbed;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.employee.Employee;
import org.jobseeker.master.Gender;
import org.jobseeker.master.IndustryType;
import org.jobseeker.master.area.City;
import org.jobseeker.master.area.Province;
import org.jobseeker.master.education.EducationLevel;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.JobLevel;
import org.jobseeker.organization.JobType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MasterMapper {

    CityDataEmbed toAttachCity(City city);

    GeneralDataEmbed toAttachProvince(Province province);

    GenderDataEmbed toAttachGender(Gender gender);

    GeneralMultiLangDataEmbed toAttachDegree(EducationLevel educationLevel);

    @Mapping(source = "logo.link", target = "logo")
    CompanyDataEmbed toAttachCompany(Company company);

    IndustryTypeDataEmbed toIndustryTypeDataEmbed(IndustryType industryType);

    GeneralDataEmbed toGeneralDataEmbed(JobLevel jobLevel);

    GeneralDataEmbed toGeneralDataEmbed(JobType jobType);

    EmployeeDataEmbed toEmployeeDataEmbed(Employee employee);
}
