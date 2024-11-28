package com.jobseeker.hrms.organization.data.basic.documentRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDataRequest {

	@NotBlank
	private String name;

	private String groupCode;

	private List<String> allowedFileTypes;
}
