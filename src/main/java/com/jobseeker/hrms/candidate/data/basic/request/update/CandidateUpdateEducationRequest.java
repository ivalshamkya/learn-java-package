package com.jobseeker.hrms.candidate.data.basic.request.update;

import com.jobseeker.hrms.candidate.data.basic.request.component.EducationDataRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdateEducationRequest {

    // Education field
    @Valid
    private List<EducationDataRequest> educations;
}
