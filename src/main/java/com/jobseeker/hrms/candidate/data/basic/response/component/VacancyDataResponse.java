package com.jobseeker.hrms.candidate.data.basic.response.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jobseeker.embedded.general.GeneralDataEmbed;

@Data
public class VacancyDataResponse {
    @JsonProperty("oid")
    private String _id;
    private String name;
    private String rrNumber;
    private GeneralDataEmbed branch;
    private GeneralDataEmbed jobLevel;
}
