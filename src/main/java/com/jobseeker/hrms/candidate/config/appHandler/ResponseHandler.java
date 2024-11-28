package com.jobseeker.hrms.candidate.config.appHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static <T> ResponseEntity<T> output(int code, HttpStatus status, String msg, Object data) {
        Map<String, Object> meta = new HashMap<String, Object>();
        meta.put("code", code);
        meta.put("status", status);
        meta.put("message", msg);

        Map<String, Object> mapResponse = new HashMap<String, Object>();
        mapResponse.put("meta", meta);
        mapResponse.put("data", data);

        //noinspection unchecked
        return new ResponseEntity<T>((T)mapResponse, status);
    }
}
