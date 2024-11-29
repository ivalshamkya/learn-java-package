package com.jobseeker.hrms.candidate.data.general.embed;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneItem {

    @NotBlank
    private String countryCode;

    @NotBlank
    private String phoneNumber;

}