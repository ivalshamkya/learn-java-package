package com.jobseeker.hrms.organization.config.appHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Map;

public class RequestHandler {

	public static <T> T remapToData(Map<Object, Object> request, Class<T> requestType) {
		ObjectMapper mapper = new ObjectMapper();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		T dataRequest = mapper.convertValue(request, requestType);

		var hasAnyError_dataRequest = validator.validate(dataRequest);
		if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

		return dataRequest;
	}
}