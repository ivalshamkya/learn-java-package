package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.data.basic.customData.CustomGroupResponse;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomData;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomGroupData;
import com.jobseeker.hrms.organization.service.basic.customGroupData.CustomGroupDataService;
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
@RequestMapping("/custom-group")
@DependsOn("customGroupDataHandlerMap")
public class CustomGroupDataController {

	private final Map<String, BaseCustomGroupData<?>> customGroupDataHandlerMap;

	public CustomGroupDataController(@Qualifier("customGroupDataHandlerMap") Map<String, BaseCustomGroupData<?>> customGroupDataHandlerMap) {
		this.customGroupDataHandlerMap = customGroupDataHandlerMap;
	}

	private BaseCustomGroupData<?> getHandler() {
		return customGroupDataHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping()
	public ResponseEntity<Object> getCustomGroupData(
			@RequestParam Map<Object, Object> params
	) {
		var data = getHandler().getPaginatedCustomGroupData(params);
		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), data);
	}
}