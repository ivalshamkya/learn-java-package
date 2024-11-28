package com.jobseeker.hrms.organization.config.appHandler;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.timezone.TimeZoneContextHolder;
import com.jobseeker.hrms.organization.config.timezone.TimeZoneConverter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

	public static <T> ResponseEntity<T> output(int code, HttpStatus status, String msg, T data) {
		TimeZoneContextHolder.setTimeZone(ZoneId.of(ServletRequestAWS.getTimezone()));
//		if (data != null) TimeZoneConverter.convertFields(data);
		if (data != null) {
			if (data instanceof Page<?>) {
				((Page<?>) data).forEach(TimeZoneConverter::convertFields);
			} else {
				TimeZoneConverter.convertFields(data);
			}
		}
		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("code", code);
		meta.put("status", status);
		meta.put("message", msg);

		Map<String, Object> mapResponse = new HashMap<String, Object>();
		mapResponse.put("meta", meta);
		mapResponse.put("data", data);

		TimeZoneContextHolder.clear();
		//noinspection unchecked
		return new ResponseEntity<T>((T)mapResponse, status);
	}
}