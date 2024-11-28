package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseDocumentRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Document Request API")
@RequestMapping("/document-request")
@DependsOn("documentRequestHandlerMap")
public class DocumentRequestController {

	private final Map<String, BaseDocumentRequest<?>> documentRequestHandlerMap;

	public DocumentRequestController(@Qualifier("documentRequestHandlerMap") Map<String, BaseDocumentRequest<?>> documentRequestHandlerMap) {
		this.documentRequestHandlerMap = documentRequestHandlerMap;
	}

	private BaseDocumentRequest<?> getHandler() {
		return documentRequestHandlerMap.get(ServletRequestAWS.getSourceApp());
	}

	@GetMapping
	public ResponseEntity<Object> getPaging(@RequestParam Map<Object, Object> param) {
		Page<?> results = getHandler().getDocumentRequests(param);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetail(@PathVariable("id") String oid) {
		Object result = getHandler().getDocumentRequest(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().createDocumentRequest(request);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String oid, @RequestBody Map<Object, Object> request) throws Exception {
		Object result = getHandler().updateDocumentRequest(request, oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String oid) throws Exception {
		String msgResponse = getHandler().deleteDocumentRequest(oid);

		return ResponseHandler.output(
				HttpStatus.OK.value(), HttpStatus.OK,
				msgResponse, null);
	}

}