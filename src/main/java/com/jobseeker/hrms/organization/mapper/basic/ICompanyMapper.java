package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.company.CompanyDataInitRequest;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataResponse;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataSimpleRequest;
import com.jobseeker.hrms.organization.data.basic.company.CompanyJLifeDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.service.MasterMapperService;
import org.jobseeker.embedded.organization.CompanyDataEmbed;
import org.jobseeker.organization.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {MasterMapperService.class})
public interface ICompanyMapper {
	@Mapping(source = "logo.link", target = "logo")
	CompanyDataEmbed toAttachCompany(Company company);

	@Mapping(source = "city.name", target = "city")
	@Mapping(source = "district.name", target = "district")
	@Mapping(source = "subDistrict.name", target = "subDistrict")
	@Mapping(source = "logo.align", target = "logoPosition")
	@Mapping(source = "expiryDate", target = "remainingDays", qualifiedByName = "setRemainingDays")
	CompanyDataResponse toCompanyDataResponse(Company company);

	@Mapping(source = "cityId", target = "city", qualifiedByName = "setCity")
	@Mapping(source = "districtId", target = "district", qualifiedByName = "setDistrict")
	@Mapping(source = "subDistrictId", target = "subDistrict", qualifiedByName = "setSubDistrict")
	@Mapping(source = "industryTypeId", target = "industryType", qualifiedByName = "setIndustryType")
	Company toInitialize(CompanyDataInitRequest initRequest);

	@Mapping(source = "logoUrl", target = "logo.link")
	@Mapping(source = "logoPosition", target = "logo.align")
	void updateFromSimpleRequest(@MappingTarget Company company, CompanyDataSimpleRequest simpleRequest);

    //<editor-fold desc="temporary support for basic version">
    @Mapping(source = "city.name", target = "city")
	@Mapping(source = "district.name", target = "district")
	@Mapping(source = "subDistrict.name", target = "subDistrict")
	@Mapping(source = "logo.align", target = "logoPosition")
	@Mapping(source = "credit.coin", target = "credit", qualifiedByName = "setToString")
	@Mapping(source = "industryType.name.en", target = "industryType.name")
	@Mapping(source = "expiryDate", target = "remainingDays", qualifiedByName = "setRemainingDays")
	CompanyJLifeDataResponse toCompanyJLifeDataResponse(Company company);

	@Named("setToString")
	default String setToString(Integer coin) {
		if (coin == null) return "";
		return coin.toString();
	}
    //</editor-fold>

}
