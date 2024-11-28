package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseImportTemplate;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Import Template API")
@RequestMapping("/import-template")
@DependsOn("importTemplateHandlerMap")
public class ImportTemplateController {

	private final Map<String, BaseImportTemplate<?>> importTemplateHandlerMap;

	public ImportTemplateController(@Qualifier("importTemplateHandlerMap") Map<String, BaseImportTemplate<?>> importTemplateHandlerMap) {
		this.importTemplateHandlerMap = importTemplateHandlerMap;
	}

	private BaseImportTemplate<?> getHandler() {
		return importTemplateHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getData(String type) {
		Object results = getHandler().getImportTemplate(type);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}
}