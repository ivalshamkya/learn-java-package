package com.jobseeker.hrms.candidate.data.basic.request.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDataRequest {
    private String companyName;

    private String positionName;

    private String startDate;

    private String endDate;

    private Integer lastSalary = 0;

    private Boolean isCurrentlyWorking = false;
}
