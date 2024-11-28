package com.jobseeker.hrms.organization.data.lws.hardSkill;

import com.jobseeker.hrms.organization.data.basic.hardSkill.HardSkillDataResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class HardSkillLawsonDataResponse extends HardSkillDataResponse {
    private String code;
}
