package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.interfaces.ICompanyHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseApproval;
import com.jobseeker.hrms.organization.service.baseContract.BaseCompany;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Company API")
@RequestMapping("/company")
@DependsOn("companyHandlerMap")
public class CompanyController {

	private final Map<String, BaseCompany<?>> companyHandlerMap;

	public CompanyController(@Qualifier("companyHandlerMap") Map<String, BaseCompany<?>> companyHandlerMap) {
		this.companyHandlerMap = companyHandlerMap;
	}

	private BaseCompany<?> getHandler() {
		return companyHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getDetail() {
		Object result = getHandler().getCompany();

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PostMapping("/init")
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().initCompany(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

	@PutMapping()
	public ResponseEntity<Object> update(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().updateCompany(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

}
