package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobType;
import com.jobseeker.hrms.organization.service.baseContract.BasePkwtTemplate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "PKWT Template API")
@RequestMapping("/pkwt-template")
@DependsOn("pkwtTemplateHandlerMap")
public class PkwtTemplateController {

	private final Map<String, BasePkwtTemplate<?>> pkwtTemplateHandlerMap;

	public PkwtTemplateController(@Qualifier("pkwtTemplateHandlerMap") Map<String, BasePkwtTemplate<?>> pkwtTemplateHandlerMap) {
		this.pkwtTemplateHandlerMap = pkwtTemplateHandlerMap;
	}

	private BasePkwtTemplate<?> getHandler() {
		return pkwtTemplateHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging() {
		List<?> results = getHandler().getPkwtTemplates();

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}
}