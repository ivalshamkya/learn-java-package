package com.jobseeker.hrms.candidate.mappers.service;

import com.jobseeker.hrms.candidate.data.basic.request.ImportCandidateRequest;
import com.jobseeker.hrms.candidate.helper.ImportHelper;
import com.jobseeker.hrms.candidate.repository.master.*;
import com.jobseeker.hrms.candidate.repository.organization.IHardSkillOrgRepository;
import com.jobseeker.hrms.candidate.repository.organization.ISoftSkillOrgRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.jobseeker.embedded.master.*;
import org.jobseeker.embedded.mediafile.FileDataEmbed;
import org.jobseeker.enums.general.GenderType;
import org.jobseeker.master.Gender;
import org.jobseeker.master.IdentityType;
import org.jobseeker.master.SocialMedia;
import org.jobseeker.master.area.Province;
import org.jobseeker.master.education.EducationLevel;
import org.jobseeker.master.education.Institution;
import org.jobseeker.master.education.Major;
import org.jobseeker.master.repositories.IdentityTypeMasterRepository;
import org.jobseeker.organization.HardSkillOrganization;
import org.jobseeker.organization.SoftSkillOrganization;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImportMapperService {

    private final IEducationLevelRepository educationLevelRepository;
    private final IGenderRepository genderRepository;
    private final IHardSkillOrgRepository hardSkillRepository;
    private final ISoftSkillOrgRepository softSkillepository;
    private final IdentityTypeMasterRepository identityTypeRepository;
    private final IInstitutionRepository institutionRepository;
    private final IMajorRepository majorRepository;
    private final IProvinceRepository provinceRepository;
    private final ISocialMediaRepository socialMediaRepository;

    //<editor-fold defaultstate="collapsed" desc="setImportCsvRequest">
    @Named("setImportCsvRequest")
    public ImportCandidateRequest setImportCsvRequest(CSVRecord record) {
        return ImportCandidateRequest.builder()
                .Name(record.get("Nama"))
                .IdentityNumber(record.get("NIK"))
                .Gender(record.get("Jenis Kelamin"))
                .Dob(record.get("Tanggal Lahir"))
                .Email(record.get("Email"))
                .PhoneNumber(record.get("Nomor Whatsapp"))
                .AddressIdentity(record.get("Alamat (KTP)"))
                .PostalCodeIdentity(record.get("Kode POS (KTP)"))
                .Address(record.get("Alamat (Domisili)"))
                .PostalCode(record.get("Kode POS (Domisili)"))
                .Disability(record.get("Disability"))
                .LastEducation(record.get("Penndidikan Terakhir"))
                .GPA(record.get("IPK/Nilai"))
                .StartDate(record.get("Tanggal Mulai"))
                .GraduationDate(record.get("Tanggal Lulus"))
                .PositionName(record.get("Nama Posisi"))
                .JobLevel(record.get("Level Pekerjaan"))
                .Industry(record.get("Industri"))
                .StartDateWorking(record.get("Tanggal Mulai Kerja"))
                .EndDateWorking(record.get("Tanggal Selesai Kerja"))
                .JobDescription(record.get("Deskripsi Pekerjaan"))
                .Mobility(record.get("Mobilitas"))
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toPhotoProfile">
    @Named("setPhotoProfile")
    public PhotoProfileDataEmbed setPhotoProfile(String PhotoProfile) {
        String extension = PhotoProfile.substring(PhotoProfile.lastIndexOf(".") + 1);

        FileDataEmbed thumbnail = new FileDataEmbed().builder()
                .link(PhotoProfile)
                .type(extension)
                .build();

        return new PhotoProfileDataEmbed().builder()
                .thumbnail(thumbnail)
                .link(PhotoProfile)
                .type(extension)
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setGender">
    @Named("setGender")
    public GenderDataEmbed setGender(String request) {
        GenderType genderType = request.equals("Laki-Laki") ? GenderType.MALE : GenderType.FEMALE;

        Gender gender = genderRepository.findByType(genderType.toString());

        return new GenderDataEmbed().builder()
                .type(genderType)
                .name(gender.getName())
                ._id(gender.get_id())
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setPhoneNumber">
    @Named("setPhoneNumber")
    public PhoneDataEmbed setPhoneNumber(String PhoneNumber) {
        // Set Phone Number
        return new PhoneDataEmbed().builder()
                .phone(PhoneNumber)
                .mobilePhone(PhoneNumber)
                .whatsapp(PhoneNumber)
                .countryCode("+62")
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setLastEducation">
    @Named("setLastEducation")
    public EducationDataEmbed setLastEducation(ImportCandidateRequest request) {
        // Set Last Education
        Major major = majorRepository.findFirstByMajorName("Lainnya")
                .orElseThrow(() -> new NoSuchElementException("Major not found"));

        Institution institution = institutionRepository.findByName("Lainnya")
                .orElseThrow(() -> new NoSuchElementException("Institution not found"));

        EducationLevel educationLevel = educationLevelRepository.findByName(request.getLastEducation());
        GeneralMultiLangDataEmbed educationLevelEmbed = new GeneralMultiLangDataEmbed().builder()
                ._id(educationLevel.get_id())
                .name(educationLevel.getName())
                .build();

        GeneralDataEmbed majorDataEmbed = new GeneralDataEmbed().builder()
                ._id(major.get_id())
                .name(major.getName())
                .build();

        String gpaReplaced = request.getGPA().replace(",", ".");

        // Set graduateDate
        return new EducationDataEmbed().builder()
                .gpa(Double.parseDouble(gpaReplaced))
                .major(majorDataEmbed)
                .degree(educationLevelEmbed)
                .startDate(ImportHelper.parseDate(request.getStartDate()))
                .isUntilNow(false)
                .graduateDate(ImportHelper.parseDate(request.getGraduationDate()))
                .institution(institution)
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setProvince">
    @Named("setProvince")
    public GeneralDataEmbed setProvince(String Province) {
        // Set Province
        Province province = provinceRepository.findByProvinceName(Province);

        return new GeneralDataEmbed().builder()
                ._id(province.get_id())
                .name(province.getName())
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setIdentityType">
    @Named("setIdentityType")
    public IdentityTypeDataEmbed setIdentityType(String IdentityNumber) {
        IdentityType identity = identityTypeRepository.findById("65fc2b5783bcf55561d8973d")
                .orElseThrow(() -> new NoSuchElementException("Identity type not found"));

        // Set Identity Number
        return new IdentityTypeDataEmbed().builder()
                .name(identity.getName())
                .number(IdentityNumber)
                ._id(identity.get_id())
                .build();

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setSocialMedia">
    @Named("setSocialMedia")
    public SosmedDataEmbed setSocialMedia(String socialMedia) {
        SocialMedia socialMediaData = Optional.ofNullable(socialMediaRepository.findByName(socialMedia))
                .orElseThrow(() -> new NoSuchElementException("Social media not found"));

        // Set Social Media
        return new SosmedDataEmbed().builder()
                ._id(socialMediaData.get_id())
                .provider(socialMediaData.getName())
                .build();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setHardSkills">
    @Named("setHardSkills")
    public List<GeneralMultiLangDataEmbed> setHardSkills(String request) {
        // Set Hard Skills
        String[] skills = request.split(",");

        List<GeneralMultiLangDataEmbed> hardSkills = new ArrayList<>();
        for (String skill : skills) {
            HardSkillOrganization skillData = hardSkillRepository.findByName(skill);
            if (Optional.ofNullable(skillData).isPresent()) {
                GeneralMultiLangDataEmbed dataEmbed = new GeneralMultiLangDataEmbed().builder()
                        ._id(skillData.get_id())
                        .name(skillData.getName())
                        .build();
                hardSkills.add(dataEmbed);
            }
        }
        return hardSkills;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setSoftSkills">
    @Named("setSoftSkills")
    public List<GeneralMultiLangDataEmbed> setSoftSkills(String request) {
        // Set Hard Skills
        String[] skills = request.split(",");

        List<GeneralMultiLangDataEmbed> softSkills = new ArrayList<>();
        for (String skill : skills) {
            SoftSkillOrganization skillData = softSkillepository.findByName(skill);
            if (Optional.ofNullable(skillData).isPresent()) {
                GeneralMultiLangDataEmbed dataEmbed = new GeneralMultiLangDataEmbed().builder()
                        ._id(skillData.get_id())
                        .name(skillData.getName())
                        .build();
                softSkills.add(dataEmbed);
            }
        }
        return softSkills;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="set">
}
