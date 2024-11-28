package com.jobseeker.hrms.organization.data.basic.filter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jobseeker.vacancy.Vacancy;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentActiveVacancyResponse {
    @JsonProperty("oid")
    private String _id;
    private String name;
    private Integer totalVacancies;

}
