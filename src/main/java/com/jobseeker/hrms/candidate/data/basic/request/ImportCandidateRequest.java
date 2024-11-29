package com.jobseeker.hrms.candidate.data.basic.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.area.CityDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportCandidateRequest {
    @NotBlank
    private String Name;

    @Size(min = 16, max = 16)
    @Pattern(
            regexp = "\\d{16}",
            message = "NIK must contain exactly 16 digits")
    private String IdentityNumber;

    @NotBlank
    private String Gender;

    @NotBlank
    private String Dob;

    @Pattern(
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email not valid"
    )
    @NotBlank
    private String Email;

    @Valid
    private String PhoneNumber;

    @NotBlank
    @Size(min = 8)
    private String Password;

    @NotBlank
    private String AddressIdentity;

    private GeneralDataEmbed ProvinceIdentity;

    private CityDataEmbed CityIdentity;

    @NotBlank
    private String PostalCodeIdentity;

    @NotBlank
    private String Address;

    private GeneralDataEmbed Province;

    private CityDataEmbed City;

    @NotBlank
    private String PostalCode;

    private String Disability;

    @NotBlank
    private String SourceInfo;

    @NotBlank
    private String SourceDetail;

    @NotBlank
    private String SIM;

    @NotBlank
    private String LastEducation;

    @NotBlank
    private String GPA;

    @NotBlank
    private String StartDate;

    private String GraduationDate;

    private String CompanyName;

    private String PositionName;

    private String JobLevel;

    private String Industry;

    private String StartDateWorking;

    private String EndDateWorking;

    private String JobDescription;

    @NotBlank
    private String Mobility;

    @NotBlank
    private String CompanyId;
}

