package com.jobseeker.hrms.candidate.data.basic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.candidate.VideoResumeDataEmbed;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.embedded.master.PhotoProfileDataEmbed;
import org.jobseeker.embedded.mediafile.FileDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatePagingResponse {
	@JsonProperty("oid")
	private String _id;
	private String email;
	private String name;
	private PhotoProfileDataEmbed photoProfile;
	private GeneralDataEmbed province;
	private GeneralDataEmbed city;
	private EducationDegreeEmbed lastEducation;
	private GeneralDataEmbed lastPosition;
	private FileDataEmbed cv;
	private VideoResumeDataEmbed videoResume;

	@Data
	@Builder
	public static class EducationDegreeEmbed {
		private GeneralDataEmbed degree;
	}
}
