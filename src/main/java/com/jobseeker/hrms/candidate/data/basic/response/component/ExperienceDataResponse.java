package com.jobseeker.hrms.candidate.data.basic.response.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.GeneralMultiLangDataEmbed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDataResponse {
    @Id
    @JsonProperty("oid")
    private String _id;

    private String companyName;

    private String position;

    private GeneralMultiLangDataEmbed jobType;

    private String jobDescription;

    private int salary = 0;

    private Date startDate;

    private Date endDate;

    private int workingPeriodInMonths = 0;

    private boolean isStillWorkHere = false;

    private Boolean isCurrentlyWorking;

    private String reasonResign;
}
