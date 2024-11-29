package com.jobseeker.hrms.candidate.data.basic.request;

import com.jobseeker.hrms.candidate.data.basic.request.component.EducationDataRequest;
import com.jobseeker.hrms.candidate.data.basic.request.component.ExperienceDataRequest;
import com.jobseeker.hrms.candidate.data.general.embed.PhoneItem;
import com.jobseeker.hrms.candidate.data.validator.FieldMustMatch;
import com.jobseeker.hrms.candidate.data.validator.MinimumAge;
import com.jobseeker.hrms.candidate.data.validator.NIK;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@FieldMustMatch.List({
        @FieldMustMatch(first = "password", second = "passwordConfirmation", message = "The password fields must match"),
        @FieldMustMatch(first = "email", second = "emailConfirmation", message = "The email fields must match")
})
public class CandidateCreateRequest {

    private String photoURL;

    @NotBlank
    private String cvURL;

    @NotBlank
    private String fullName;

    @NIK(isRequired = true)
    private String nik;

    @NotBlank
    @Email
    private String email;
    @Email
    private String emailConfirmation;

    @NotBlank
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String passwordConfirmation;

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

    // Education field
    @Valid
    private List<EducationDataRequest> educations;

    // Working Experiences field
    private List<ExperienceDataRequest> workingExperiences;

    private boolean freshGraduate = false;

    private long expectedSalary;
}
