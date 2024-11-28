package com.jobseeker.hrms.candidate.controller;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.candidate.data.basic.response.CandidateHistoryResponse;
import com.jobseeker.hrms.candidate.services.BaseCandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@Tag(name = "Candidate API")
@RequestMapping("/candidate")
@DependsOn("candidateHandlerMap")
public class CandidateController {

    private final Map<String, BaseCandidateService<?>> baseCandidate;

    public CandidateController(@Qualifier("candidateHandlerMap") Map<String, BaseCandidateService<?>> baseCandidate) {
        this.baseCandidate = baseCandidate;
    }

    private BaseCandidateService<?> getHandler() {
        return baseCandidate.get(ServletRequestAWS.getSourceApp());
    }

    @GetMapping
    public ResponseEntity<Object> getPaging(@Valid @RequestParam Map<Object, Object> request) throws Exception {
        Page<?> results = getHandler().getCandidates(request);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<Object> createCandidate(@Valid @RequestBody Map<Object, Object> request) {
        var results = getHandler().createCandidate(request);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCandidate(@Valid @PathVariable("id") String applicantId) throws Exception {
        var results = getHandler().getCandidateByApplicantId(applicantId);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getCandidateProfileById(
            @Valid @PathVariable("id") String candidateId
    ) {
        Object results = getHandler().getCandidateProfileById(candidateId);
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<CandidateHistoryResponse> getItems(@Valid @PathVariable("id") String candidateId) {
        Object results = getHandler().getCandidateHistory(candidateId);
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }

    @GetMapping("/export")
    public ResponseEntity<Object> exportToExcel(@RequestParam Map<Object, Object> request) throws Exception {
        try {
            byte[] generatedExcel = (byte[]) getHandler().exportToExcel(request);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", "candidates.xlsx");

            return new ResponseEntity<>(generatedExcel, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reminder candidate to complete profile (Mail)
    @PostMapping("/{candidateId}/reminders/complete-profile")
    public ResponseEntity<Object> sendReminderCompleteProfile(@Valid @PathVariable("candidateId") String candidateId) {
        getHandler().sendReminderCompleteProfile(candidateId);
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_SEND_DATA.getMessage(), null);
    }

    @PostMapping("/import")
    public ResponseEntity<Object> importCsv(@RequestBody Map<Object, Object> request) throws Exception {
        getHandler().importCsv(request);

        return ResponseHandler.output(
                HttpStatus.CREATED.value(), HttpStatus.CREATED,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), true);
    }
}
