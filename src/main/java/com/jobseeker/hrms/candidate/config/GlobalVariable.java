package com.jobseeker.hrms.candidate.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GlobalVariable {

    private double lon = 106.806890;
    private double lat = -6.618798;
    private String defaultBirthdate = "2000-01-01T00:00:00.000Z";
}
