package com.jobseeker.hrms.candidate.data.basic.request.update;

import com.jobseeker.hrms.candidate.data.basic.request.component.EducationDataRequest;
import com.jobseeker.hrms.candidate.data.basic.request.component.ExperienceDataRequest;
import com.jobseeker.hrms.candidate.data.general.embed.PhoneItem;
import com.jobseeker.hrms.candidate.data.validator.MinimumAge;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdatePersonalInformationRequest {

    private String photoURL;

    @NotBlank
    private String cvURL;

    @NotBlank
    private String fullName;

    @Size(min = 16, max = 16)
    @Pattern(
            regexp = "\\d{16}",
            message = "NIK must contain exactly 16 digits")
    private String nik;

    @NotBlank
    @Email
    private String email;
    @Email
    private String emailConfirmation;

    @Valid
    private PhoneItem phoneNumber;

    @NotBlank
    @MinimumAge(value = 17)
    private String birthDate;

    @NotBlank
    private String genderId;

    @NotBlank
    private String provinceId;

    @NotBlank
    private String cityId;
}
