package com.jobseeker.hrms.organization.mapper.basic;

import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataRequest;
import com.jobseeker.hrms.organization.data.basic.documentRequest.DocumentRequestDataResponse;
import org.jobseeker.organization.DocumentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IDocumentRequestMapper {

	DocumentRequestDataResponse toDocumentRequestDataResponse(DocumentRequest documentRequest);

	void toWriteDocumentRequest(@MappingTarget DocumentRequest documentRequest, DocumentRequestDataRequest request);

//	PositionDataResponse toPositionDataResponse(Position position);
//	void toWritePosition(@MappingTarget Position position, PositionDataRequest request);
}
