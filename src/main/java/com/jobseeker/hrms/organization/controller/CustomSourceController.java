package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomGroupData;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomSource;
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
@Tag(name = "Custom Candidate eSource API")
@RequestMapping("/custom-sources")
@DependsOn("customSourceHandlerMap")
public class CustomSourceController {

	private final Map<String, BaseCustomSource<?>> customSourceHandlerMap;

	public CustomSourceController(@Qualifier("customSourceHandlerMap") Map<String, BaseCustomSource<?>> customSourceHandlerMap) {
		this.customSourceHandlerMap = customSourceHandlerMap;
	}

	private BaseCustomSource<?> getHandler() {
		return customSourceHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(Map<Object, Object> param) {
		Page<?> results = getHandler().getCustomSources(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}
}