package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.jobType.PaginationJobTypeParam;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobType;
import com.jobseeker.hrms.organization.service.baseContract.BaseVacancyTemplate;
import org.jobseeker.data.PaginationParam;
import com.jobseeker.hrms.organization.interfaces.IJobTypeHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Job Type API")
@RequestMapping("/job-type")
@DependsOn("jobTypeHandlerMap")
public class JobTypeController {

	private final Map<String, BaseJobType<?>> jobTypeHandlerMap;

	public JobTypeController(@Qualifier("jobTypeHandlerMap") Map<String, BaseJobType<?>> jobTypeHandlerMap) {
		this.jobTypeHandlerMap = jobTypeHandlerMap;
	}

	private BaseJobType<?> getHandler() {
		return jobTypeHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(@Valid @RequestParam Map<Object, Object> param) {
		Page<?> results = getHandler().getJobTypes(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetail(@PathVariable("id") String oid) {
		Object result = getHandler().getJobType(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().createJobType(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String oid, @RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().updateJobType(request, oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String oid) throws Exception {
		String msgResponse = getHandler().deleteJobType(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				msgResponse, null);
	}

}