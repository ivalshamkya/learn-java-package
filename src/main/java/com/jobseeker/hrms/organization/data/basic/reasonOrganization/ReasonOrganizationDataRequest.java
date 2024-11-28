package com.jobseeker.hrms.organization.data.basic.reasonOrganization;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.jobseeker.data.PaginationParam;

@Data
@NoArgsConstructor
public class ReasonOrganizationDataRequest extends PaginationParam {
    @Nullable
    @Pattern(regexp = "^(withdraw|reject|blacklist)$",
        message = "Field allowed input: withdraw or reject or blacklist")
    private String type;
}
