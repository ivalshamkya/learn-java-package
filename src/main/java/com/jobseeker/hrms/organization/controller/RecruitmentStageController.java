package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.service.baseContract.BasePkwtTemplate;
import com.jobseeker.hrms.organization.service.baseContract.BaseRecruitmentStage;
import org.jobseeker.data.PaginationParam;
import com.jobseeker.hrms.organization.interfaces.IRecruitmentStageHandler;
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
@Tag(name = "Recruitment Stage API")
@RequestMapping("/recruitment-stage")
@DependsOn("recruitmentStageHandlerMap")
public class RecruitmentStageController {

	private final Map<String, BaseRecruitmentStage<?>> recruitmentStageHandlerMap;

	public RecruitmentStageController(@Qualifier("recruitmentStageHandlerMap") Map<String, BaseRecruitmentStage<?>> recruitmentStageHandlerMap) {
		this.recruitmentStageHandlerMap = recruitmentStageHandlerMap;
	}

	private BaseRecruitmentStage<?> getHandler() {
		return recruitmentStageHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(@Valid @RequestParam Map<Object, Object> param) {
		Page<?> results = getHandler().getRecruitmentStages(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetail(@PathVariable("id") String oid) {
		Object result = getHandler().getRecruitmentStage(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().createRecruitmentStage(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String oid, @RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().updateRecruitmentStage(request, oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String oid) throws Exception {
		String msgResponse = getHandler().deleteRecruitmentStage(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				msgResponse, null);
	}

}