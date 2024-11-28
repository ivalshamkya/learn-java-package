package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataResponse;
import com.jobseeker.hrms.organization.service.baseContract.BaseApproval;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomData;
import com.jobseeker.hrms.organization.service.basic.customData.CustomDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Custom Data API")
@RequestMapping("/custom-data")
@DependsOn("customDataHandlerMap")
public class CustomDataController {

	private final Map<String, BaseCustomData<?>> customDataHandlerMap;

	public CustomDataController(@Qualifier("customDataHandlerMap") Map<String, BaseCustomData<?>> customDataHandlerMap) {
		this.customDataHandlerMap = customDataHandlerMap;
	}

	private BaseCustomData<?> getHandler() {
		return customDataHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping()
	public ResponseEntity<Object> getCustomData(
			@RequestParam Map<Object, Object> params
	) {
		var data = getHandler().getPaginatedCustomData(params);
		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), data);
	}
}