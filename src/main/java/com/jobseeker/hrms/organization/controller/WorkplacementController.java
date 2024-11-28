package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseWorkplacement;
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
@Tag(name = "Workplacement API")
@RequestMapping("/workplacement")
@DependsOn("workplacementHandlerMap")
public class WorkplacementController {
	private final Map<String, BaseWorkplacement<?>> baseWorkplacement;

	@Autowired
	public WorkplacementController(@Qualifier("workplacementHandlerMap") Map<String, BaseWorkplacement<?>> baseWorkplacement) {
		this.baseWorkplacement = baseWorkplacement;
	}

	private BaseWorkplacement<?> getWorkplacementHandler() {
		return baseWorkplacement.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(@RequestParam Map<Object, Object> param) {
		Page<?> results = getWorkplacementHandler().getWorkplacements(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetail(@PathVariable("id") String oid) {
		Object result = getWorkplacementHandler().getWorkplacement(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getWorkplacementHandler().createWorkplacement(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String oid, @RequestBody Map<Object, Object> request) throws Exception {
		Object result = getWorkplacementHandler().updateWorkplacement(request, oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String oid) throws Exception {
		String msgResponse = getWorkplacementHandler().deleteWorkplacement(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				msgResponse, null);
	}

}