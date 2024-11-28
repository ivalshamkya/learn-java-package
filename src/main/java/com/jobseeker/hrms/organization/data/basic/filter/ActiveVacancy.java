package com.jobseeker.hrms.organization.data.basic.filter;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jobseeker.vacancy.Vacancy;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ActiveVacancy extends Vacancy {
    @Field("total_vacancies")
    private Integer totalVacancies;
}
