package com.jobseeker.hrms.candidate.data.basic.response.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jobseeker.embedded.general.GeneralDataEmbed;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CandidateSimpleDataResponse extends GeneralDataEmbed {
    private String email;
}
