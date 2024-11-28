package com.jobseeker.hrms.organization.data.lws.candidateLanguage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.organization.CompanyDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateLanguageLWSDataResponse {
    @JsonProperty("oid")
    private String _id;

    private String name;

    private String code;

    private CompanyDataEmbed company;
}
