package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.interfaces.IOfferingLetterHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobType;
import com.jobseeker.hrms.organization.service.baseContract.BaseOfferingLetter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Offering Letter API")
@RequestMapping("/offering-letter")
@DependsOn("offeringLetterHandlerMap")
public class OfferingLetterController {

	private final Map<String, BaseOfferingLetter<?>> offeringLetterHandlerMap;

	public OfferingLetterController(@Qualifier("offeringLetterHandlerMap") Map<String, BaseOfferingLetter<?>> offeringLetterHandlerMap) {
		this.offeringLetterHandlerMap = offeringLetterHandlerMap;
	}

	private BaseOfferingLetter<?> getHandler() {
		return offeringLetterHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping()
	public ResponseEntity<Object> getDetail() {
		Object result = getHandler().getOfferingLetter();
		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PutMapping()
	public ResponseEntity<Object> update(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().updateOfferingLetter(request);
		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
	}

}