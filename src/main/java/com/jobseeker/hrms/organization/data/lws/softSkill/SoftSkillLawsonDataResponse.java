package com.jobseeker.hrms.organization.data.lws.softSkill;

import com.jobseeker.hrms.organization.data.basic.softSkill.SoftSkillDataResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SoftSkillLawsonDataResponse extends SoftSkillDataResponse {
    private String code;
}
