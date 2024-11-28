package com.jobseeker.hrms.candidate.data.basic.response.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.vacancy.VacancyDataEmbed;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDataResponse {

    @JsonProperty("oid")
    private String _id;

    private VacancyDataEmbed vacancy;

    @JsonProperty("histories")
    private List<ApplicantActivityDataResponse> historyActivity;

}
