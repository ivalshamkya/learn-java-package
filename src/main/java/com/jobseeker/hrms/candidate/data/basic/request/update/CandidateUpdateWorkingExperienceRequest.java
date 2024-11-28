package com.jobseeker.hrms.candidate.data.basic.request.update;

import com.jobseeker.hrms.candidate.data.basic.request.component.ExperienceDataRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdateWorkingExperienceRequest {

    // Working Experiences field
    private List<ExperienceDataRequest> workingExperiences;

    private long expectedSalary;

    private boolean freshGraduate = false;
}
