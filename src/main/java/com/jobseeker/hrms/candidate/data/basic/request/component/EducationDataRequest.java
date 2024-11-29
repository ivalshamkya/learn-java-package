package com.jobseeker.hrms.candidate.data.basic.request.component;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDataRequest {

    @NotBlank
    private String educationId;

    @NotBlank
    private String institutionId;

    @NotBlank
    private String majorId;

    private double gpa;

    private String startDate;

    private String endDate;

    private Boolean isStillStudy = false;

}
