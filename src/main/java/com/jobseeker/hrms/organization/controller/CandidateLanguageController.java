package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseCandidateLanguage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Candidate Language API")
@RequestMapping("/candidate-language")
@DependsOn("candidateLanguageHandlerMap")
public class CandidateLanguageController {
    private final Map<String, BaseCandidateLanguage<?>> candidateLanguageHandlerMap;

    public CandidateLanguageController(@Qualifier("candidateLanguageHandlerMap")  Map<String, BaseCandidateLanguage<?>> candidateLanguageHandlerMap) {
        this.candidateLanguageHandlerMap = candidateLanguageHandlerMap;
    }

    private BaseCandidateLanguage<?> getHandler() {
        return candidateLanguageHandlerMap.get(ServletRequestAWS.getSourceApp());
    }

    @GetMapping
    public ResponseEntity<Object> getCandidateLanguages(@Valid @RequestParam Map<Object, Object> request) throws Exception {
        Object result = getHandler().getCandidateLanguages(request);

        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
}
