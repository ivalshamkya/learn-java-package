package com.jobseeker.hrms.candidate.controller;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.candidate.services.BaseCareersiteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Candidate API")
@RequestMapping("/candidate")
@DependsOn("careersiteHandlerMap")
public class CareersiteController {

    private final Map<String, BaseCareersiteService<?>> baseCareersite;

    public CareersiteController(@Qualifier("careersiteHandlerMap") Map<String, BaseCareersiteService<?>> baseCareersite) {
        this.baseCareersite = baseCareersite;
    }

    private BaseCareersiteService<?> getHandler() {
        return baseCareersite.get(ServletRequestAWS.getSourceApp());
    }

    @PostMapping()
    public ResponseEntity<Object> createCandidate(@RequestBody Map<Object, Object> request) {
        Object results = getHandler().createCandidate(request);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Object> update(
            @Valid @RequestBody Map<Object, Object> request,
            @RequestParam(required = false) String step
    ) {
        Object results = getHandler().updateCandidate(request, step);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), results);
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getCandidateProfile() {
        var results = getHandler().getCandidateProfile();
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }
}
