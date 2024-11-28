package com.jobseeker.hrms.candidate.data.basic.response.explore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.candidate.VideoResumeDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.master.PhotoProfileDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExploreDataReponse {

    @JsonProperty("oid")
    private String _id;

    private String name;

    private String position;

    private PhotoProfileDataEmbed photoProfile;

    private VideoResumeDataEmbed videoResume;

    private Integer distance;

    private GeneralDataEmbed city;

    private GeneralDataEmbed district;

    private GeneralDataEmbed province;

    private GeneralDataEmbed lastEducation;

    private Boolean isFromMyPool = false;
}
