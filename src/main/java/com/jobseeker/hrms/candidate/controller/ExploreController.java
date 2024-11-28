package com.jobseeker.hrms.candidate.controller;

import com.jobseeker.hrms.candidate.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.candidate.data.basic.request.explore.ExploreDataRequest;
import com.jobseeker.hrms.candidate.services.explore.ExploreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Candidate API")
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class ExploreController {

    private final ExploreService exploreService;

    @GetMapping("/explore")
    public ResponseEntity<Object> explore(@Valid @ModelAttribute ExploreDataRequest exploreDataRequest) throws Exception {
        Object results = exploreService.findCandidate(exploreDataRequest);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

}
