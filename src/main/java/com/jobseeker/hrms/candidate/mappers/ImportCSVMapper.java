package com.jobseeker.hrms.candidate.mappers;

import com.jobseeker.hrms.candidate.data.basic.request.ImportCandidateRequest;
import com.jobseeker.hrms.candidate.helper.ImportHelper;
import com.jobseeker.hrms.candidate.mappers.service.ImportMapperService;
import org.apache.commons.csv.CSVRecord;
import org.jobseeker.candidate.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring", uses = {
        ImportMapperService.class })
public interface ImportCSVMapper {

    @Mapping(source = ".", target = ".", qualifiedByName = "setImportCsvRequest")
    ImportCandidateRequest toImportRequest(CSVRecord record);

    @Mapping(source = "gender", target = "gender", qualifiedByName = "setGender")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "setPhoneNumber")
    @Mapping(source = ".", target = "lastEducation", qualifiedByName = "setLastEducation")
    @Mapping(source = "dob", target = "dob", qualifiedByName = "setDate")
    Candidate toSaveCandidate(ImportCandidateRequest request);

    @Named("setDate")
    default Date setDate(String date) {
        return ImportHelper.parseDate(date);
    }
}
