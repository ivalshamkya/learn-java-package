package com.jobseeker.hrms.organization.mapper.basic;

import org.jobseeker.embedded.area.CityDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.master.IndustryTypeDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.embedded.vacancy.JobFunctionDataEmbed;
import org.jobseeker.master.IndustryType;
import org.jobseeker.master.JobFunction;
import org.jobseeker.master.area.City;
import org.jobseeker.master.area.District;
import org.jobseeker.master.area.SubDistrict;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IMasterMapper {

	JobFunctionDataEmbed toAttachJobFunctionDataEmbed(JobFunction jobFunction);

	CityDataEmbed toCityDataEmbed(City city);

	GeneralDataEmbed toAttachDistrict(District district);

	GeneralDataEmbed toAttachSubDistrict(SubDistrict subDistrict);

	@Mapping(target = "img", qualifiedByName = "setImgFileDataEmbed")
	IndustryTypeDataEmbed toAttachIndustryType(IndustryType industryType);

	@Named("setImgFileDataEmbed")
	default Object setImgFileDataEmbed(String img) {
		FileDataEmbed fileDataEmbed = new FileDataEmbed();
		fileDataEmbed.setLink(img);
		return fileDataEmbed;
	}
}
