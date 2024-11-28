package com.jobseeker.hrms.candidate.data.basic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jobseeker.hrms.candidate.config.serializer.DateTimezoneSerializer;
import com.jobseeker.hrms.candidate.data.basic.response.component.ExperienceDataResponse;
import com.jobseeker.hrms.candidate.data.basic.response.component.VacancyDataResponse;
import com.jobseeker.hrms.candidate.data.general.embed.PhoneItem;
import com.jobseeker.hrms.candidate.data.general.response.EducationDataResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.candidate.VideoResumeDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.master.PhotoProfileDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponse {

    @JsonProperty("oid")
    private String _id;

    private String email;

    private String name;

    private String position;

    private PhotoProfileDataEmbed photoProfile;

    private FileDataEmbed cv;

    private VideoResumeDataEmbed videoResume;

    private GeneralDataEmbed province;

    private GeneralDataEmbed city;

    private GeneralDataEmbed gender;

    private String age;

    private PhoneItem phoneNumber;

    @JsonSerialize(using = DateTimezoneSerializer.class)
    private Date birthdate;

    private List<EducationDataResponse> educations;

    private List<ExperienceDataResponse> experiences;

    private EducationDataResponse lastEducation;

    private VacancyDataResponse lastPosition;

    private Long expectedSalary;

    private String nik;

    private String ktp;

}
