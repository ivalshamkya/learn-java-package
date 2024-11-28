package com.jobseeker.hrms.organization.data.basic.softSkill;

import com.jobseeker.hrms.organization.data.basic.general.ObjectMultiLanguageDataRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoftSkillDataRequest {
    @Valid
    private ObjectMultiLanguageDataRequest name;
    private String code;
}
