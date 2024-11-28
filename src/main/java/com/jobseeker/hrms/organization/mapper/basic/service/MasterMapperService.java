package com.jobseeker.hrms.organization.mapper.basic.service;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IMasterMapper;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.area.CityDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.master.IndustryTypeDataEmbed;
import org.jobseeker.master.IndustryType;
import org.jobseeker.master.area.City;
import org.jobseeker.master.area.District;
import org.jobseeker.master.area.SubDistrict;
import org.jobseeker.master.repositories.IndustryTypeMasterRepository;
import org.jobseeker.master.repositories.area.CityMasterRepository;
import org.jobseeker.master.repositories.area.DistrictMasterRepository;
import org.jobseeker.master.repositories.area.SubDistrictMasterRepository;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MasterMapperService {

	private final CityMasterRepository cityRepository;
	private final DistrictMasterRepository districtRepository;
	private final SubDistrictMasterRepository subDistrictRepository;
	private final IndustryTypeMasterRepository industryTypeRepository;

	private final IMasterMapper masterMapper;

	@Named("setRemainingDays")
	public Integer setRemainingDays(Date expiryDate) {
		if (expiryDate == null) return null;
		LocalDate expDate = expiryDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate currentDate = LocalDate.now();

		long remainingDays = ChronoUnit.DAYS.between(currentDate, expDate);
		return Math.toIntExact(remainingDays);
	}


	@Named("setCity")
	public CityDataEmbed setCity(String cityId) {
		if (cityId == null) return null;
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_CITY_FOUND.getMsg()));
		return masterMapper.toCityDataEmbed(city);
	}

	@Named("setDistrict")
	public GeneralDataEmbed setDistrict(String districtId) {
		if (districtId == null) return null;
		District district = districtRepository.findById(districtId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DISTRICT_FOUND.getMsg()));
		return masterMapper.toAttachDistrict(district);
	}

	@Named("setSubDistrict")
	public GeneralDataEmbed setSubDistrict(String subDistrictId) {
		if (subDistrictId == null) return null;
		SubDistrict subDistrict = subDistrictRepository.findById(subDistrictId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_SUB_DISTRICT_FOUND.getMsg()));
		return masterMapper.toAttachSubDistrict(subDistrict);
	}

	@Named("setCoordinate")
	public GeoJsonPoint setCoordinate(String longlat) {
		String[] points = Optional.ofNullable(longlat).orElse("0,0").split(",\\s*");
		double latitude = Double.parseDouble(points[0]);
		double longitude = Double.parseDouble(points[1]);
		return new GeoJsonPoint(longitude, latitude);
	}

	@Named("setIndustryType")
	public IndustryTypeDataEmbed setIndustryType(String industryTypeId) {
		if (industryTypeId == null) return null;
		IndustryType industryType = industryTypeRepository.findById(industryTypeId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_INDUSTRY_TYPE_FOUND.getMsg()));
		return masterMapper.toAttachIndustryType(industryType);
	}

}
