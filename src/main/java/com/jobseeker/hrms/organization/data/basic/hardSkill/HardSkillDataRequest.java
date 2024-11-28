package com.jobseeker.hrms.organization.data.basic.hardSkill;

import com.jobseeker.hrms.organization.data.basic.general.ObjectMultiLanguageDataRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jobseeker.embedded.general.ObjectMultiLanguage;
import org.jobseeker.enums.general.StatusData;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HardSkillDataRequest {
    @Valid
    private ObjectMultiLanguageDataRequest name;
    private String code;
}
