package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataRequest;
import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataResponse;
import org.jobseeker.organization.OfferingLetter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IOfferingLetterMapper {

	@Mapping(source = "letterHead.link", target = "letterHead")
	@Mapping(source = "signature.link", target = "signature")
	OfferingLetterDataResponse toOfferingLetterDataResponse(OfferingLetter offeringLetter);


	@Mapping(source = "letterHead", target = "letterHead.link")
	@Mapping(source = "signature", target = "signature.link")
	void toWriteOfferingLetter(@MappingTarget OfferingLetter offeringLetter, OfferingLetterDataRequest request);
}
