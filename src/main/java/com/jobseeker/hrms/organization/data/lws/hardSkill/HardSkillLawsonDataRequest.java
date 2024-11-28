package com.jobseeker.hrms.organization.data.lws.hardSkill;

import com.jobseeker.hrms.organization.data.basic.general.ObjectMultiLanguageDataRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HardSkillLawsonDataRequest {
    @Valid
    private ObjectMultiLanguageDataRequest name;
    
    @NotBlank
    private String code;
}
