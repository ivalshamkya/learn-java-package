package com.jobseeker.hrms.candidate.mappers;

import com.jobseeker.hrms.candidate.data.basic.request.CandidateCreateRequest;
import com.jobseeker.hrms.candidate.data.basic.request.update.CandidateUpdateEducationRequest;
import com.jobseeker.hrms.candidate.data.basic.request.update.CandidateUpdatePersonalInformationRequest;
import com.jobseeker.hrms.candidate.data.basic.request.CandidateUploadResumeRequest;
import com.jobseeker.hrms.candidate.data.basic.request.component.ApplicantActivityDataRequest;
import com.jobseeker.hrms.candidate.data.basic.request.update.CandidateUpdateWorkingExperienceRequest;
import com.jobseeker.hrms.candidate.data.basic.response.CandidatePagingResponse;
import com.jobseeker.hrms.candidate.data.basic.response.CandidateResponse;
import com.jobseeker.hrms.candidate.data.basic.response.component.ApplicantActivityDataResponse;
import com.jobseeker.hrms.candidate.data.basic.response.component.CandidateSimpleDataResponse;
import com.jobseeker.hrms.candidate.data.basic.response.component.HistoryDataResponse;
import com.jobseeker.hrms.candidate.data.basic.response.component.VacancyDataResponse;
import com.jobseeker.hrms.candidate.mappers.service.CandidateMapperService;
import com.jobseeker.hrms.candidate.mappers.service.GeneralMapperService;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.candidate.CandidateDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.vacancy.applicant.ApplicantActivityDataEmbed;
import org.jobseeker.embedded.vacancy.applicant.activity.OfferingActivityDataEmbed;
import org.jobseeker.embedded.vacancy.applicant.activity.ProcessActivityDataEmbed;
import org.jobseeker.helper.ObjectMapperHelper;
import org.jobseeker.vacancy.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        IGlobalMapper.class, CandidateMapperService.class, GeneralMapperService.class
})
public interface ICandidateMapper {

    @Mapping(target = "photoProfile.link", qualifiedByName = "setPrefixURLPhotoProfile")
    @Mapping(target = "cv", qualifiedByName = "setCvObjectToFileDataEmbed")
    @Mapping(target = "videoResume.videoResume", qualifiedByName = "setPrefixURLVideoResume")
    @Mapping(source = "videoResume.videoResume", target = "videoResume.link", qualifiedByName = "setPrefixURLVideoResume")
    @Mapping(source = ".", target = "age", qualifiedByName = "setAgeFromCandidate")
    @Mapping(source = ".", target = "birthdate", qualifiedByName = "setBirthdateFromCandidate")
    @Mapping(target = "gender", qualifiedByName = "setGenderResponse")
    @Mapping(source = "identityType.file", target = "ktp")
    @Mapping(source = "identityType.number", target = "nik")
    @Mapping(source = ".", target = "phoneNumber", qualifiedByName = "setPhoneItemFromCandidate")
    @Mapping(target = "educations", qualifiedByName = "setEducationsResponse")
    @Mapping(target = "experiences", qualifiedByName = "setExperiencesResponse")
    @Mapping(target = "lastEducation", qualifiedByName = "setLastEducationResponse")
    CandidateResponse toResponse(Candidate candidate);

    @Mapping(target = "lastEducation.degree", qualifiedByName = "setGeneralDataFromMultiLang")
    @Mapping(target = "cv", qualifiedByName = "setFileDataEmbedResponse")
    @Mapping(target = "photoProfile.link", qualifiedByName = "setPrefixURLPhotoProfile")
    CandidatePagingResponse toPagingResponse(Candidate candidate);

    VacancyDataResponse toVacancySimpleData(GeneralDataEmbed generalDataEmbed);

    //<editor-fold desc="To Save">
    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "freshGraduate", target = "isFreshGraduate")
    @Mapping(source = "birthDate", target = "dob", qualifiedByName = "setStringToDate")
    @Mapping(source = "genderId", target = "gender", qualifiedByName = "setGender")
    @Mapping(source = "cityId", target = "city", qualifiedByName = "setCity")
    @Mapping(source = "provinceId", target = "province", qualifiedByName = "setProvince")
    @Mapping(source = "workingExperiences", target = "experiences", qualifiedByName = "setExperience")
    @Mapping(source = "educations", target = "educations", qualifiedByName = "setEducation")
    @Mapping(source = "nik", target = "identityType", qualifiedByName = "setIdentity")
    @Mapping(target = "phoneNumber", qualifiedByName = "setPhone")
    @Mapping(source = "cvURL", target = "cv", qualifiedByName = "setFile")
    @Mapping(source = "photoURL", target = "photoProfile", qualifiedByName = "setPhotoProfile")
    Candidate toSave(CandidateCreateRequest request);

    // for upload resume
    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "freshGraduate", target = "isFreshGraduate")
    @Mapping(source = "birthDate", target = "dob", qualifiedByName = "setStringToDate")
    @Mapping(source = "genderId", target = "gender", qualifiedByName = "setGender")
    @Mapping(source = "cityId", target = "city", qualifiedByName = "setCity")
    @Mapping(source = "provinceId", target = "province", qualifiedByName = "setProvince")
    @Mapping(source = "workingExperiences", target = "experiences", qualifiedByName = "setExperience")
    @Mapping(source = "educations", target = "educations", qualifiedByName = "setEducation")
    @Mapping(source = "nik", target = "identityType", qualifiedByName = "setIdentity")
    @Mapping(target = "phoneNumber", qualifiedByName = "setPhone")
    @Mapping(source = "cvURL", target = "cv", qualifiedByName = "setFile")
    @Mapping(source = "photoURL", target = "photoProfile", qualifiedByName = "setPhotoProfile")
    Candidate toSave(CandidateUploadResumeRequest request);
    //</editor-fold>

    //<editor-fold desc="To Update">
    @Mapping(source = "fullName", target = "name")
    @Mapping(source = "birthDate", target = "dob", qualifiedByName = "setStringToDate")
    @Mapping(source = "genderId", target = "gender", qualifiedByName = "setGender")
    @Mapping(source = "cityId", target = "city", qualifiedByName = "setCity")
    @Mapping(source = "provinceId", target = "province", qualifiedByName = "setProvince")
    @Mapping(source = "nik", target = "identityType", qualifiedByName = "setIdentity")
    @Mapping(target = "phoneNumber", qualifiedByName = "setPhone")
    @Mapping(source = "cvURL", target = "cv", qualifiedByName = "setFile")
    @Mapping(source = "photoURL", target = "photoProfile", qualifiedByName = "setPhotoProfile")
    void toUpdatePersonalInformation(@MappingTarget Candidate candidate, CandidateUpdatePersonalInformationRequest request);
    //</editor-fold>

    //<editor-fold desc="To Update Working Experience">
    @Mapping(source = "freshGraduate", target = "isFreshGraduate")
    @Mapping(source = "workingExperiences", target = "experiences", qualifiedByName = "setExperience")
    void toUpdateWorkingExperiences(@MappingTarget Candidate candidate, CandidateUpdateWorkingExperienceRequest request);
    //</editor-fold>

    //<editor-fold desc="To Update Education">
    @Mapping(source = "educations", target = "educations", qualifiedByName = "setEducation")
    void toUpdateEducations(@MappingTarget Candidate candidate, CandidateUpdateEducationRequest request);
    //</editor-fold>

    ApplicantActivityDataRequest toApplicantActivityItem(ApplicantActivityDataEmbed applicantActivityDataEmbed);

    //<editor-fold desc="Mapper Candidate to Candidate Data Embed">
    @Mapping(source = "photoProfile", target = "photoProfile")
    @Mapping(source = "videoResume.link", target = "videoResume")
    @Mapping(target = "cv", qualifiedByName = "setFileDataEmbedToString")
    @Mapping(source = "dob", target = "age", qualifiedByName = "setCountAge")
    @Mapping(source = "gender.type", target = "gender")
    @Mapping(source = "lastEducation.gpa", target = "gpa")
    @Mapping(source = "lastEducation.pointEducation", target = "pointEducation")
    @Mapping(source = "lastEducation", target = "lastEducation")
    @Mapping(source = "experiences", target = "lastExperience", qualifiedByName = "setLastExperience")
    @Mapping(target = "jobFunction", qualifiedByName = "setJobFunctionDataEmbedToSingle")
    @Mapping(source = "registeredFrom", target = "source", qualifiedByName = "setSource")
    @Mapping(source = "isFreshGraduate", target = "freshGraduate")
    CandidateDataEmbed toCandidateDataEmbed(Candidate candidate);
    //</editor-fold>

    CandidateSimpleDataResponse toCandidateSimpleData(Candidate candidate);

    @Mapping(target = "historyActivity", qualifiedByName = "toApplicantActivityDataEmbed")
    HistoryDataResponse toHistoryData(Applicant applicant);

    List<HistoryDataResponse> toHistoriesData(List<Applicant> applicants);

    @Named("toApplicantActivityDataEmbed")
    default ApplicantActivityDataResponse toApplicantActivityDataEmbed(Object data) {
        return switch (data) {
            case null -> null;
            case ProcessActivityDataEmbed processData -> toApplicantActivityDataResponse(processData);
            case OfferingActivityDataEmbed offeringData -> toApplicantActivityDataResponse(offeringData);
            default -> toApplicantActivityDataResponse(
                    ObjectMapperHelper.Convert(data, ApplicantActivityDataEmbed.class)
            );
        };
    }


    @Mapping(target = "file", ignore = true)
    @Mapping(source = "createdAt", target = "processAt")
    ApplicantActivityDataResponse toApplicantActivityDataResponse(ApplicantActivityDataEmbed applicantActivityDataEmbed);

    @Mapping(source = "file.link", target = "file")
    @Mapping(source = "createdAt", target = "processAt")
    ApplicantActivityDataResponse toApplicantActivityDataResponse(ProcessActivityDataEmbed applicantActivityDataEmbed);

    @Mapping(target = "file", ignore = true)
    @Mapping(source = "createdAt", target = "processAt")
    ApplicantActivityDataResponse toApplicantActivityDataResponse(OfferingActivityDataEmbed applicantActivityDataEmbed);
}
