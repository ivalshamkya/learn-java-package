package com.jobseeker.hrms.candidate.data.basic.request.component;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaDataRequest {

    @NotBlank
    @JsonProperty("oid")
    @JsonAlias("socialMediaId")
    private String _id;

    @JsonProperty("username")
    private String socialMediaUsername;

    @NotBlank
//    @JsonProperty("link")
    private String socialMediaLink;
}
