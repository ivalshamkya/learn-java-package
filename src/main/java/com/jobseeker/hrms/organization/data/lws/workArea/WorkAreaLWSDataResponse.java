package com.jobseeker.hrms.organization.data.lws.workArea;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkAreaLWSDataResponse {

	@JsonProperty("oid")
	private String _id;
	private String name;
	private GeneralDataEmbed branch;
}
