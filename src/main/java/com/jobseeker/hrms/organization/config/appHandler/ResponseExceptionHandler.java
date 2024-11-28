package com.jobseeker.hrms.organization.config.appHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ResponseExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, List<String>> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err -> {
			List<String> errorMsg = errors.get(err.getField());
			if(errorMsg == null) {
				errorMsg = new ArrayList<String>();
			}
			errorMsg.add(err.getDefaultMessage());
			errors.put(err.getField(), errorMsg);
		});

		Map<String, Object> result = new HashMap<>();
		result.put("errors", errors);

		return ResponseHandler.output(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST,
				"Data request sent is not appropriate", result
		);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> notValuePresent(NoSuchElementException ex, HttpServletRequest request) {
		// StackTraceElement[] stackTraceElements = ex.getStackTrace();
		return ResponseHandler.output(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY,
				ex.getMessage(), null
		);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> throwException(Exception ex, HttpServletRequest request) {
		System.out.println(ex);
		StackTraceElement[] stackTraceElements = ex.getStackTrace();
		return ResponseHandler.output(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getClass() +"-"+ ex.getMessage() + " | " + stackTraceElements[0], null
		);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<?> throwDuplicateException(Exception ex, HttpServletRequest request) {
		return ResponseHandler.output(
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST,
				"Data being requested is exist", null
		);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, List<String>> errors = new HashMap<>();
		
		ex.getConstraintViolations().forEach(violation -> {
			String field = violation.getPropertyPath().toString();
			List<String> errorMsg = errors.get(field);
			if (errorMsg == null) {
				errorMsg = new ArrayList<>();
			}
			errorMsg.add(violation.getMessage());
			errors.put(field, errorMsg);
		});
		
		Map<String, Object> result = new HashMap<>();
		result.put("errors", errors);
		
		return ResponseHandler.output(
			HttpStatus.UNPROCESSABLE_ENTITY.value(),
			HttpStatus.UNPROCESSABLE_ENTITY,
			"Data request sent is not appropriate", result
		);
	}
}
