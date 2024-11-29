package com.jobseeker.hrms.candidate.mappers.service;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.data.basic.request.component.EducationDataRequest;
import com.jobseeker.hrms.candidate.data.basic.request.component.ExperienceDataRequest;
import com.jobseeker.hrms.candidate.data.basic.request.component.SocialMediaDataRequest;
import com.jobseeker.hrms.candidate.data.general.embed.PhoneItem;
import com.jobseeker.hrms.candidate.data.general.response.EducationDataResponse;
import com.jobseeker.hrms.candidate.data.general.response.IdentityTypeDataResponse;
import com.jobseeker.hrms.candidate.mappers.MasterMapper;
import lombok.RequiredArgsConstructor;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.deprecated._PhoneDataEmbed;
import org.jobseeker.embedded.area.CityDataEmbed;
import org.jobseeker.embedded.employee.EmployeeDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.master.*;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.embedded.vacancy.JobFunctionDataEmbed;
import org.jobseeker.employee.Employee;
import org.jobseeker.employee.repositories.EmployeeRepository;
import org.jobseeker.enums.general.GenderType;
import org.jobseeker.enums.general.PrefixURL;
import org.jobseeker.enums.general.SourceRegistered;
import org.jobseeker.helper.CheckURLSourceFile;
import org.jobseeker.helper.GeneralHelper;
import org.jobseeker.helper.ObjectMapperHelper;
import org.jobseeker.master.*;
import org.jobseeker.master.area.City;
import org.jobseeker.master.area.District;
import org.jobseeker.master.area.Province;
import org.jobseeker.master.area.SubDistrict;
import org.jobseeker.master.education.EducationLevel;
import org.jobseeker.master.education.Institution;
import org.jobseeker.master.education.Major;
import org.jobseeker.master.repositories.*;
import org.jobseeker.master.repositories.area.CityMasterRepository;
import org.jobseeker.master.repositories.area.DistrictMasterRepository;
import org.jobseeker.master.repositories.area.ProvinceMasterRepository;
import org.jobseeker.master.repositories.area.SubDistrictMasterRepository;
import org.jobseeker.master.repositories.education.EducationLevelMasterRepository;
import org.jobseeker.master.repositories.education.InstitutionMasterRepository;
import org.jobseeker.master.repositories.education.MajorMasterRepository;
import org.jobseeker.organization.JobLevel;
import org.jobseeker.organization.JobType;
import org.jobseeker.organization.repositories.JobLevelOrgRepository;
import org.jobseeker.organization.repositories.JobTypeOrgRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CandidateMapperService {

    private final MasterMapper masterMapper;
    private final GeneralMapperService generalMapper;

    private final CityMasterRepository cityRepository;
    private final ProvinceMasterRepository provinceRepository;
    private final GenderMasterRepository genderRepositry;
    private final EducationLevelMasterRepository educationLevelRepository;
    private final IdentityTypeMasterRepository identityTypeRepository;
    private final InstitutionMasterRepository institutionRepository;
    private final MajorMasterRepository majorRepository;
    private final JobTypeOrgRepository jobTypeOrgRepository;
    private final JobLevelOrgRepository jobLevelOrgRepository;

    // Master
    private final ReligionMasterRepository religionRepository;
    private final DisabilityMasterRepository disabilityRepository;
    private final MaritalStatusMasterRepository maritalStatusRepository;
    private final DistrictMasterRepository districtRepository;
    private final SubDistrictMasterRepository subDistrictRepository;
    private final SIMLicenseMasterRepository simLicenseRepository;
    private final SocialMediaMasterRepository socialMediaRepository;
    private final IndustryTypeMasterRepository industryTypeRepository;

    // Employee
    private final EmployeeRepository employeeRepository;

    @Named("setPhotoProfile")
    public PhotoProfileDataEmbed setPhotoProfile(String photoLink) {
        if (photoLink == null || photoLink.isEmpty()) return null;

        String thumbnailLink = GeneralHelper.getThumbnailImage(photoLink);
        FileDataEmbed thumbnail = new FileDataEmbed();
        thumbnail.setLink(thumbnailLink);
        thumbnail.setType(GeneralHelper.getFileExtension(thumbnailLink));

        PhotoProfileDataEmbed photoProfileEmbedded = new PhotoProfileDataEmbed();
        photoProfileEmbedded.setLink(photoLink);
        photoProfileEmbedded.setType(GeneralHelper.getFileExtension(photoLink));
        photoProfileEmbedded.setThumbnail(thumbnail);
        return photoProfileEmbedded;
    }

    @Named("setCvFromObject")
    public FileDataEmbed setCv(Object cv) {
        if (cv == null) return null;

        FileDataEmbed result = new FileDataEmbed();

        if (cv instanceof String data) {
            result.setLink(data);
        } else if (cv instanceof FileDataEmbed data) {
            result.setLink(data.getLink());
            result.setType(data.getType());
        }

        return result;
    }

    @Named("setNationality")
    public GeneralDataEmbed setNationality(String nationality) {
        if (nationality == null || nationality.isEmpty()) return null;
        GeneralDataEmbed generalDataEmbed = new GeneralDataEmbed();
        generalDataEmbed.setName(nationality);
        return generalDataEmbed;
    }

    @Named("setIdentity")
    public IdentityTypeDataEmbed setIdentity(String nik) {
        if (nik == null || nik.isEmpty()) return null;
        IdentityType identityType = identityTypeRepository.findFirstByCode("ktp")
                .orElseThrow(() -> new NoSuchElementException("IdentityType not found"));
        IdentityTypeDataEmbed candidateIdentityType = new IdentityTypeDataEmbed();
        candidateIdentityType.set_id(identityType.get_id());
        candidateIdentityType.setName(identityType.getName());
        candidateIdentityType.setNumber(nik);
        return candidateIdentityType;
    }

    @Named("setGender")
    public GenderDataEmbed setGenderCandidate(String genderId) {
        if (genderId == null || genderId.isEmpty()) return null;
        Gender gender = genderRepositry.findById(genderId)
                .orElseThrow(() -> new NoSuchElementException("Gender not found"));
        GenderDataEmbed attachedGender = masterMapper.toAttachGender(gender);
        attachedGender.setType(GenderType.get(gender.getName().getEn().toLowerCase()));
        return attachedGender;
    }

    @Named("setReligion")
    public GeneralMultiLangDataEmbed setReligion(String religionId) {
        if (religionId == null || religionId.isEmpty()) return null;
        Religion religion = religionRepository.findById(religionId)
                .orElseThrow(() -> new NoSuchElementException("Religion data not found"));
        GeneralMultiLangDataEmbed data = new GeneralMultiLangDataEmbed();
        data.setName(religion.getName());
        data.set_id(religion.get_id());
        return data;
    }

    @Named("setPlaceOfBirth")
    public GeneralDataEmbed setPlaceOfBirth(String id) {
        if (id == null || id.isEmpty()) return null;
        City pob = cityRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Place of birth data not found"));
        GeneralDataEmbed data = new GeneralDataEmbed();
        data.setName(pob.getName());
        data.set_id(pob.get_id());
        return data;
    }

    @Named("setCountAge")
    public Integer setCountAge(Date data) {
        if (data == null) return 0;
        LocalDate date = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(LocalDate.from(date), LocalDate.now());
        System.out.println("birthdate >>> " + period.getYears());
        return period.getYears();
    }

    @Named("setProvince")
    public GeneralDataEmbed setProvince(String provinceId) {
        if (provinceId == null || provinceId.isEmpty()) return null;
        Province province = provinceRepository.findById(provinceId).orElse(null);
        return masterMapper.toAttachProvince(province);
    }

    @Named("setCity")
    public CityDataEmbed setCity(String cityId) {
        if (cityId == null || cityId.isEmpty()) return null;
        City city = cityRepository.findById(cityId).orElse(null);
        return masterMapper.toAttachCity(city);
    }

    @Named("setCityToGeneralData")
    public GeneralDataEmbed setCityToGeneralData(String cityId) {
        if (cityId == null || cityId.isEmpty()) return null;
        City city = cityRepository.findById(cityId)
                .orElse(new City());

        GeneralDataEmbed generalDataEmbed = new GeneralDataEmbed();
        generalDataEmbed.set_id(city.get_id());
        generalDataEmbed.setName(city.getName());
        return generalDataEmbed;

    }

    @Named("setDistrict")
    public GeneralDataEmbed setDistrict(String districtId) {
        if (districtId == null || districtId.isEmpty()) return null;
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new NoSuchElementException("District not found"));
        GeneralDataEmbed generalDataEmbed = new GeneralDataEmbed();
        generalDataEmbed.set_id(district.get_id());
        generalDataEmbed.setName(district.getName());
        return generalDataEmbed;
    }

    @Named("setSubDistrict")
    public GeneralDataEmbed setSubDistrict(String subDistrictId) {
        if (subDistrictId == null || subDistrictId.isEmpty()) return null;
        SubDistrict subDistrict = subDistrictRepository.findById(subDistrictId)
                .orElseThrow(() -> new NoSuchElementException("Sub District not found"));
        GeneralDataEmbed generalDataEmbed = new GeneralDataEmbed();
        generalDataEmbed.set_id(subDistrict.get_id());
        generalDataEmbed.setName(subDistrict.getName());
        return generalDataEmbed;
    }

    @Named("setToInstantTime")
    public Instant setToInstantTime(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        return HelperMapperService.convertToInstant(dateStr);
    }

    @Named("setEducation")
    public EducationDataEmbed setEducation(EducationDataRequest requestEdu) {

        EducationLevel educationLevel = educationLevelRepository.findById(requestEdu.getEducationId())
                .orElseThrow(() -> new NoSuchElementException("Education level not found"));
        GeneralDataEmbed institutionEmbed = new GeneralDataEmbed();
        GeneralDataEmbed majorEmbed = new GeneralDataEmbed();

        if (requestEdu.getInstitutionId() != null) {
            Institution institution = institutionRepository.findById(requestEdu.getInstitutionId())
                    .orElseThrow(() -> new NoSuchElementException("Institution not found"));

            institutionEmbed.set_id(institution.get_id());
            institutionEmbed.setName(institution.getName());
        }

        if (requestEdu.getMajorId() != null) {
            Major major = majorRepository.findById(requestEdu.getMajorId())
                    .orElseThrow(() -> new NoSuchElementException("Major not found"));

            majorEmbed.set_id(major.get_id());
            majorEmbed.setName(major.getName());
        }

        EducationDataEmbed educationDataEmbed = new EducationDataEmbed();
        educationDataEmbed.setDegree(masterMapper.toAttachDegree(educationLevel));
        educationDataEmbed.setInstitution(institutionEmbed);
        educationDataEmbed.setMajor(majorEmbed);
        educationDataEmbed.setGpa(requestEdu.getGpa());
        educationDataEmbed.setStartDate(GeneralHelper.convertToDate(requestEdu.getStartDate()));
        educationDataEmbed.setGraduateDate(GeneralHelper.convertToDate(requestEdu.getEndDate()));
        educationDataEmbed.setUntilNow(requestEdu.getIsStillStudy());
        educationDataEmbed.setPointEducation(educationLevel.getPoint());

        return educationDataEmbed;
    }

    @Named("setDegree")
    public GeneralMultiLangDataEmbed setDegree(String degreeId) {
        if (degreeId == null) return null;
        EducationLevel educationLevel = getEducationLevel(degreeId);
        return masterMapper.toAttachDegree(educationLevel);
    }

    @Named("setPointEducation")
    public int setPointEducation(String degreeId) {
        if (degreeId == null) return 0;
        EducationLevel educationLevel = getEducationLevel(degreeId);
        return educationLevel.getPoint();
    }

    protected EducationLevel getEducationLevel(String educationLevelId) {
        return educationLevelRepository.findById(educationLevelId)
                .orElseThrow(() -> new NoSuchElementException("Education level not found"));
    }

    @Named("setInstituion")
    public GeneralDataEmbed setInstituion(String institutionId) {
        if (institutionId == null) return null;
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new NoSuchElementException("Institution not found"));

        GeneralDataEmbed institutionEmbed = new GeneralDataEmbed();
        institutionEmbed.set_id(institution.get_id());
        institutionEmbed.setName(institution.getName());
        return institutionEmbed;
    }

    @Named("setMajor")
    public GeneralDataEmbed setMajor(String majorId) {
        if (majorId == null) return null;
        Major major = majorRepository.findById(majorId)
                .orElseThrow(() -> new NoSuchElementException("Major not found"));

        GeneralDataEmbed majorEmbed = new GeneralDataEmbed();
        majorEmbed.set_id(major.get_id());
        majorEmbed.setName(major.getName());
        return majorEmbed;
    }

    @Named("setWorkingPeriodInMonths")
    public Integer setWorkingPeriodInMonths(Date startDate, Date endDate, Boolean isCurrentlyWorking) {
        if (startDate == null || (startDate == null && endDate == null)) return 0;
        if (endDate == null && isCurrentlyWorking) endDate = new Date();
        if (endDate == null) return 0;

        long workInMonths = ChronoUnit.MONTHS.between(
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
        return Math.toIntExact(workInMonths);
    }

    @Named("setFile")
    public FileDataEmbed setFile(String fileLink) {
        if (fileLink == null) return null;
        FileDataEmbed fileDataEmbed = new FileDataEmbed();
        fileDataEmbed.setLink(fileLink);
        fileDataEmbed.setType(HelperMapperService.getFileExtension(fileLink));
        return fileDataEmbed;
    }

    @Named("setPhone")
    public PhoneDataEmbed setPhone(PhoneItem phoneNumber) {
        PhoneDataEmbed phoneDataEmbed = new PhoneDataEmbed();
        phoneDataEmbed.setCountryCode(phoneNumber.getCountryCode());
        phoneDataEmbed.setPhone(phoneNumber.getPhoneNumber());
        return phoneDataEmbed;
    }

    @Named("setPhoneFromString")
    public PhoneDataEmbed setPhoneFromString(String phoneNumber) {
        if (phoneNumber == null) return null;
        PhoneDataEmbed phoneDataEmbed = new PhoneDataEmbed();
        phoneDataEmbed.setPhone(phoneNumber);
        return phoneDataEmbed;
    }

    @Named("setJobType")
    public GeneralDataEmbed setJobType(String jobTypeId) {
        if (jobTypeId == null || jobTypeId.isEmpty()) return null;
        JobType jobType = jobTypeOrgRepository.findById(jobTypeId)
                .orElseThrow(() -> new NoSuchElementException("JobType not found"));
        return masterMapper.toGeneralDataEmbed(jobType);
    }

    @Named("setJobLevel")
    public GeneralDataEmbed setJobLevel(String jobLevelId) {
        if (jobLevelId == null || jobLevelId.isEmpty()) return null;
        JobLevel jobLevel = jobLevelOrgRepository.findById(jobLevelId)
                .orElseThrow(() -> new NoSuchElementException("Job Level not found"));
        return masterMapper.toGeneralDataEmbed(jobLevel);
    }

    @Named("setIndustryType")
    public IndustryTypeDataEmbed setIndustryType(String industryTypeId) {
        if (industryTypeId == null || industryTypeId.isEmpty()) return null;
        IndustryType industryType = industryTypeRepository.findById(industryTypeId)
                .orElseThrow(() -> new NoSuchElementException("Industry not found"));
        return masterMapper.toIndustryTypeDataEmbed(industryType);
    }

    @Named("setDisability")
    public GeneralMultiLangDataEmbed setDisability(String disabilityId) {
        if (disabilityId == null || disabilityId.isEmpty()) return null;
        Disability disability = disabilityRepository.findById(disabilityId)
                .orElseThrow(() -> new NoSuchElementException("Disability data not found"));
        GeneralMultiLangDataEmbed data = new GeneralMultiLangDataEmbed();
        data.setName(disability.getName());
        data.set_id(disability.get_id());
        return data;
    }

    @Named("setMaritalStatus")
    public GeneralMultiLangDataEmbed setMaritalStatus(String maritalId) {
        if (maritalId == null) return null;
        MaritalStatus maritalStatus = maritalStatusRepository.findById(maritalId)
                .orElseThrow(() -> new NoSuchElementException("Marital status data not found"));
        GeneralMultiLangDataEmbed data = new GeneralMultiLangDataEmbed();
        data.setName(maritalStatus.getName());
        data.set_id(maritalStatus.get_id());
        return data;
    }

    @Named("setEmployee")
    public EmployeeDataEmbed setEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        EmployeeDataEmbed employeeDataEmbed = new EmployeeDataEmbed();
        employeeDataEmbed.set_id(employee.get_id());
        employeeDataEmbed.setEmployeeCode(employee.getEmployeeCode());
        employeeDataEmbed.setEmail(employee.getEmail());
        employeeDataEmbed.setName(employee.getName());
        return employeeDataEmbed;
    }

    @Named("setSIM")
    public List<GeneralDataEmbed> setSim(List<String> simIds) {
        if (simIds == null || simIds.isEmpty()) return null;
        List<GeneralDataEmbed> results = new ArrayList<>();
        for (String simId : simIds) {
            SIMLicense license = simLicenseRepository.findById(simId)
                    .orElseThrow(() -> new NoSuchElementException("SIM License not found"));
            GeneralDataEmbed generalDataEmbed = new GeneralDataEmbed();
            generalDataEmbed.set_id(license.get_id());
            generalDataEmbed.setName(license.getName());
            results.add(generalDataEmbed);
        }
        return results;
    }

    @Named("setExperience")
    public ExperienceDataEmbed setExperience(ExperienceDataRequest experienceItem) {
        if (experienceItem == null) return null;

        ExperienceDataEmbed experience = new ExperienceDataEmbed();
        experience.setCompanyName(experienceItem.getCompanyName());
        experience.setPosition(experienceItem.getPositionName());
        experience.setStartDate(GeneralHelper.convertToDate(experienceItem.getStartDate()));

        ZoneId zoneIdClient = ZoneId.of(ServletRequestAWS.getTimezone());
        Instant endDate = new Date().toInstant();

        if (!experienceItem.getIsCurrentlyWorking()) {
            experience.setEndDate(GeneralHelper.convertToDate(experienceItem.getEndDate()));
            endDate = GeneralHelper.convertToInstant(experienceItem.getEndDate());
        } else {
            experience.setEndDate(new Date());
        }

        long workInMonths = ChronoUnit.MONTHS.between(
                GeneralHelper.convertToInstant(experienceItem.getStartDate()).atZone(zoneIdClient),
                endDate.atZone(zoneIdClient)
        );

        experience.setWorkingPeriodInMonths((int) workInMonths);
        experience.setIsStillWorkHere(experienceItem.getIsCurrentlyWorking());
        return experience;
    }

    @Named("setSocialMedia")
    public List<SosmedDataEmbed> setSocialMedia(List<SocialMediaDataRequest> socialDataRequests) {
        if (socialDataRequests == null || socialDataRequests.isEmpty()) return null;

        List<SosmedDataEmbed> sosmedDataEmbeds = new ArrayList<>();
        for (SocialMediaDataRequest socialMediaDataRequest : socialDataRequests) {
            SocialMedia socialMedia = socialMediaRepository.findById(socialMediaDataRequest.get_id())
                    .orElseThrow(() -> new NoSuchElementException("Social media not found"));
            SosmedDataEmbed sosmedDataEmbed = new SosmedDataEmbed();
            sosmedDataEmbed.set_id(socialMedia.get_id());
            sosmedDataEmbed.setProvider(socialMedia.getName());
            sosmedDataEmbed.setLink(socialMediaDataRequest.getSocialMediaLink());

            sosmedDataEmbeds.add(sosmedDataEmbed);
        }
        return sosmedDataEmbeds;
    }

    // <------------------------------------------------------------------------------------------------------> //

    //<editor-fold desc="Candidate Mapper Response">
    @Named("setGenderResponse")
    public GeneralDataEmbed setGenderResponse(GenderDataEmbed gender) {
        if (gender == null) return null;
        if (gender.getType() == null) return null;

        GeneralDataEmbed attachedGender = new GeneralDataEmbed();
        Map<String, String> mapName = gender.getName().toMap();

        attachedGender.set_id(Optional.ofNullable(gender.get_id()).orElse(gender.getType().toString()));
        attachedGender.setName(mapName.get(ServletRequestAWS.getLanguage()));
        return attachedGender;
    }

    @Named("setGenderToMultiLangResponse")
    public GeneralMultiLangDataEmbed setGenderMultiLang(GenderDataEmbed data) {
        if (data == null) return null;

        GeneralMultiLangDataEmbed dataEmbed = new GeneralMultiLangDataEmbed();
        dataEmbed.set_id(data.get_id());
        dataEmbed.setName(data.getName());
        return dataEmbed;
    }

    @Named("setEducationsResponse")
    public List<EducationDataResponse> setEducationsResponse(List<EducationDataEmbed> embeddedList) {
        if (embeddedList == null || embeddedList.isEmpty()) {
            return null;
        }

        embeddedList.sort(Comparator.comparing(EducationDataEmbed::getPointEducation).reversed());
        List<EducationDataResponse> responses = new ArrayList<>();

        for (EducationDataEmbed data : embeddedList) {
            Date graduateDate = data.getGraduateDate();
            if (data.isUntilNow()) graduateDate = GeneralHelper.convertToDate(String.valueOf(LocalDate.now()));

            EducationDataResponse res = EducationDataResponse.builder()
                    .degree(GeneralDataEmbed.builder()
                            ._id(data.getDegree().get_id())
                            .name(HelperMapperService.setMultiLang(data.getDegree().getName()))
                            .build())
                    .gpa(data.getGpa())
                    .graduateDate(graduateDate)
                    .major(generalMapper.setGeneralData(data.getMajor()))
                    .institution(generalMapper.setGeneralData(data.getInstitution()))
                    .isUntilNow(data.isUntilNow())
                    .startDate(data.getStartDate())
                    .pointEducation(data.getPointEducation())
                    .build();
            responses.add(res);
        }
        return responses;
    }

    @Named("setEducationResponse")
    public EducationDataResponse setEducationResponse(EducationDataEmbed data) {
        if (data == null) {
            return null;
        }

        Date graduateDate = data.getGraduateDate();
        if (data.isUntilNow()) graduateDate = GeneralHelper.convertToDate(String.valueOf(LocalDate.now()));

        return EducationDataResponse.builder()
                .degree(GeneralDataEmbed.builder()
                        ._id(data.getDegree().get_id())
                        .name(HelperMapperService.setMultiLang(data.getDegree().getName()))
                        .build())
                .gpa(data.getGpa())
                .graduateDate(graduateDate)
                .major(generalMapper.setGeneralData(data.getMajor()))
                .institution(generalMapper.setGeneralData(data.getInstitution()))
                .isUntilNow(data.isUntilNow())
                .startDate(data.getStartDate())
                .pointEducation(data.getPointEducation())
                .build();
    }

    @Named("setLastEducationResponse")
    public EducationDataResponse setLastEducationResponse(List<EducationDataEmbed> embeddedList) {
        if (embeddedList == null || embeddedList.isEmpty()) return null;

        embeddedList.sort(Comparator.comparing(EducationDataEmbed::getPointEducation).reversed());

        EducationDataEmbed data = embeddedList.getFirst();

        EducationDataResponse res = EducationDataResponse.builder()
                .degree(GeneralDataEmbed.builder()
                        ._id(data.getDegree().get_id())
                        .name(HelperMapperService.setMultiLang(data.getDegree().getName()))
                        .build())
                .gpa(data.getGpa())
                .graduateDate(data.getGraduateDate())
                .major(generalMapper.setGeneralData(data.getMajor()))
                .institution(generalMapper.setGeneralData(data.getInstitution()))
                .isUntilNow(data.isUntilNow())
                .startDate(data.getStartDate())
                .pointEducation(data.getPointEducation())
                .build();

        return res;
    }

    @Named("setLastEducationResponse")
    public EducationDataResponse setLastEducationResponse(EducationDataEmbed data) {
        if (data == null) return null;
        if (data.getDegree() == null) return null;

        EducationDataResponse res = EducationDataResponse.builder()
                .degree(GeneralDataEmbed.builder()
                        ._id(Optional.of(data)
                                .map(EducationDataEmbed::getDegree)
                                .map(GeneralMultiLangDataEmbed::get_id)
                                .orElse(null)
                        )
                        .name(HelperMapperService.setMultiLang(data.getDegree().getName()))
                        .build())
                .gpa(data.getGpa())
                .graduateDate(data.getGraduateDate())
                .major(generalMapper.setGeneralData(data.getMajor()))
                .institution(generalMapper.setGeneralData(data.getInstitution()))
                .isUntilNow(data.isUntilNow())
                .startDate(data.getStartDate())
                .pointEducation(data.getPointEducation())
                .build();

        return res;
    }

    @Named("setIdentityTypeResponse")
    public IdentityTypeDataResponse setIdentityTypeResponse(IdentityTypeDataEmbed data) {
        if (data == null) return null;

        Map<String, String> dataName = data.getName().toMap();

        IdentityTypeDataResponse res = IdentityTypeDataResponse.builder()
                ._id(data.get_id())
                .name(dataName.get(ServletRequestAWS.getLanguage()))
                .file(data.getFile())
                .number(data.getNumber())
                .expiredDate(data.getExpiredDate())
                .build();

        return res;
    }
    //</editor-fold>

    @Named("setJobFunctionDataEmbedToSingle")
    public GeneralMultiLangDataEmbed setListGenericMultiLanguageObjectToSingle(
            List<JobFunctionDataEmbed> dataList
    ) {
        if (dataList == null) return null;
        GeneralMultiLangDataEmbed object = new GeneralMultiLangDataEmbed();
        object.set_id(dataList.getFirst().get_id());
        return object;
    }

    @Named("setLastExperience")
    public ExperienceDataEmbed setLastExperience(List<ExperienceDataEmbed> experiences) {
        if (experiences == null || experiences.isEmpty()) return null;

        return experiences.stream()
                // Filter by experiences that are still active or have a valid endDate
                .max(Comparator.comparing((ExperienceDataEmbed exp) ->
                        exp.getIsStillWorkHere() ? Long.MAX_VALUE :
                                Optional.ofNullable(exp.getEndDate()).map(Date::getTime).orElse(Long.MIN_VALUE))
                )
                .orElse(null);
    }

    @Named("setSource")
    public String setSource(SourceRegistered source) {
        if (source == null) return null;
        return source.name();
    }

    @Named("setCvObjectToFileDataEmbed")
    public FileDataEmbed setFileDataEmbedResponse(Object dataCv) {
        if (dataCv == null) return null;
        FileDataEmbed response = new FileDataEmbed();

        if (dataCv instanceof Map<?, ?> obj) {
            FileDataEmbed cv = ObjectMapperHelper.Convert(obj, FileDataEmbed.class);
            String link = Optional.ofNullable(cv.getLink())
                    .map(this::setPrefixURLCV)
                    .orElse(
                            Optional.ofNullable(cv.getLink())
                                    .map(this::setPrefixURLCV)
                                    .orElse(null)
                    );

            String type = Optional.of(cv)
                    .map(FileDataEmbed::getType)
                    .orElse(null);

            response = FileDataEmbed.builder()
                    .link(link)
                    .type(type)
                    .build();
        } else if (dataCv instanceof FileDataEmbed) {
            response = (FileDataEmbed) dataCv;
        } else if (dataCv instanceof String) {
            response.setLink(setPrefixURLCV((String) dataCv));
        }

        return response;
    }

    @Named("setPrefixURLCV")
    public String setPrefixURLCV(String cv) {
        if (cv == null) return null;
        return CheckURLSourceFile.addPrefixURL(cv, PrefixURL.CV);
    }

    @Named("setPrefixURLPhotoProfile")
    public String setPrefixURLPhotoProfile(String photo) {
        if (photo == null) return null;
        return CheckURLSourceFile.addPrefixURL(photo, PrefixURL.PHOTO);
    }

    @Named("setPrefixURLVideoResume")
    public String setPrefixURLVideoResume(String video) {
        if (video == null) return null;
        return CheckURLSourceFile.addPrefixURL(video, PrefixURL.VIDEO);
    }

    @Named("setPhoneItemFromCandidate")
    public PhoneItem setPhoneItemFromCandidate(Candidate candidate) {
        if (candidate == null) return null;

        // check is field phone_number exist or has value
        String phoneNumber = Optional.of(candidate)
                .map(Candidate::getPhoneNumber)
                .map(PhoneDataEmbed::getPhone)
                .orElse(null);
        String countryCode = Optional.of(candidate)
                .map(Candidate::getPhoneNumber)
                .map(PhoneDataEmbed::getCountryCode)
                .orElse(null);

        if (phoneNumber == null) {
            phoneNumber = Optional.of(candidate)
                    .map(Candidate::getPhone)
                    .map(_PhoneDataEmbed::getNumber)
                    .orElse(null);
            countryCode = Optional.of(candidate)
                    .map(Candidate::getPhone)
                    .map(_PhoneDataEmbed::getCall_code_country)
                    .orElse(null);
        }

        return PhoneItem.builder()
                .countryCode(countryCode)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Named("setAgeFromCandidate")
    public String setAgeFromCandidate(Candidate candidate) {
        if (candidate == null) return null;
        Integer age = null;
        Date dob = getBirthDate(candidate);

        if (dob != null) {
            LocalDate birthDateLocal = LocalDateTime.ofInstant(dob.toInstant(), ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();

            if (birthDateLocal.isAfter(currentDate)) {
                return "Invalid birthdate";
            }

            age = Period.between(birthDateLocal, currentDate).getYears();
        }
        return String.valueOf(age);
    }

    @Named("setBirthdateFromCandidate")
    public Date setBirthdateFromCandidate(Candidate candidate) {
        if (candidate == null) return null;
        return getBirthDate(candidate);
    }

    private Date getBirthDate(Candidate candidate) {
        Date dob = Optional.of(candidate)
                .map(Candidate::getDob)
                .orElse(null);

        if (dob == null) {
            dob = Optional.of(candidate)
                    .map(Candidate::getBirthdate)
                    .map(Date::from)
                    .orElse(null);
        }
        return dob;
    }
}