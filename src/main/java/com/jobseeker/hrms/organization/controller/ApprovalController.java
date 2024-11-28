package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseApproval;
import com.jobseeker.hrms.organization.service.baseContract.BaseBranch;
import org.jobseeker.data.PaginationParam;
import com.jobseeker.hrms.organization.interfaces.IApprovalHandler;
import com.jobseeker.hrms.organization.interfaces.IBranchHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Approval API")
@RequestMapping("/approval")
@DependsOn("approvalHandlerMap")
public class ApprovalController {

	private final Map<String, BaseApproval<?>> approvalHandlerMap;

	public ApprovalController(@Qualifier("approvalHandlerMap") Map<String, BaseApproval<?>> approvalHandlerMap) {
		this.approvalHandlerMap = approvalHandlerMap;
	}

	private BaseApproval<?> getHandler() {
		return approvalHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(@Valid @RequestParam Map<Object, Object> param) {
		Page<?> results = getHandler().getApprovals(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().createApproval(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}
}
