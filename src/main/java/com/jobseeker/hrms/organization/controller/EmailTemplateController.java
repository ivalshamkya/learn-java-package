package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseDocumentRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseEmailTemplate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Email Template API")
@RequestMapping("/email-templates")
@DependsOn("emailTemplateHandlerMap")
public class EmailTemplateController {
	private final Map<String, BaseEmailTemplate<?>> emailTemplateHandlerMap;

	public EmailTemplateController(@Qualifier("emailTemplateHandlerMap") Map<String, BaseEmailTemplate<?>> emailTemplateHandlerMap) {
		this.emailTemplateHandlerMap = emailTemplateHandlerMap;
	}

	private BaseEmailTemplate<?> getHandler() {
		return emailTemplateHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(Map<Object, Object> param) {
		Page<?> results = getHandler().getEmailTemplates(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}
}