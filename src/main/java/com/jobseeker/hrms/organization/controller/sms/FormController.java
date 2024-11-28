package com.jobseeker.hrms.organization.controller.sms;

import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.data.sms.form.FormDataResponse;
import com.jobseeker.hrms.organization.service.sms.form.FormSMSService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Custom SMS API")
@RequestMapping("/sms/interview-form")
public class FormController {
	private final FormSMSService formService;

	@GetMapping()
	public ResponseEntity<Object> getForm(
			String code
	) {
		List<FormDataResponse> data = formService.getFormByCode(code);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), data);
	}
}