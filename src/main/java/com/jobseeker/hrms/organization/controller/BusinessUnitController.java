package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseBusinessUnit;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Business Unit API")
@RequestMapping("/business-unit")
@DependsOn("businessUnitHandlerMap")
public class BusinessUnitController {

	private final Map<String, BaseBusinessUnit<?>> baseBusinessUnit;

	@Autowired
	public BusinessUnitController(@Qualifier("businessUnitHandlerMap") Map<String, BaseBusinessUnit<?>> baseBusinessUnit) {
		this.baseBusinessUnit = baseBusinessUnit;
	}

	private BaseBusinessUnit<?> getBusinessUnitHandler() {
		return baseBusinessUnit.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(@RequestParam Map<Object, Object> param) {
		Page<?> results = getBusinessUnitHandler().getBusinessUnits(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetail(@PathVariable("id") String oid) {
		Object result = getBusinessUnitHandler().getBusinessUnit(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getBusinessUnitHandler().createBusinessUnit(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String oid, @RequestBody Map<Object, Object> request) throws Exception {
		Object result = getBusinessUnitHandler().updateBusinessUnit(request, oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String oid) throws Exception {
		String msgResponse = getBusinessUnitHandler().deleteBusinessUnit(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				msgResponse, null);
	}
}