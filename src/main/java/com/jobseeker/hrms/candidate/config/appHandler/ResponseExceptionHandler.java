package com.jobseeker.hrms.candidate.config.appHandler;


import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
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
            if (errorMsg == null) {
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

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementException(NoSuchElementException ex) {
        String msg = Optional.ofNullable(ex.getMessage())
                .orElse("No Element Found");
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        int idxParenthesis = msg.indexOf("(");

        if (idxParenthesis != -1) msg = msg.substring(0, idxParenthesis).trim();
        msg = msg.replace("\"", "");

        return ResponseHandler.output(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                msg + " | " + stackTraceElements[0], null
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        String msg = Optional.ofNullable(ex.getMessage())
                .orElse("");
        int idxParenthesis = msg.indexOf("(");

        if (idxParenthesis != -1) msg = msg.substring(0, idxParenthesis).trim();
        msg = msg.replace("\"", "");

        return ResponseHandler.output(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                msg, null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> throwException(Exception ex, HttpServletRequest request) {
        String msg = Optional.ofNullable(ex.getMessage())
                .orElse("");
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        int idxParenthesis = msg != null ? msg.indexOf("(") : 0;

        if (idxParenthesis != -1) msg = msg.substring(0, idxParenthesis).trim();
        msg = msg.replace("\"", "");

        if (ServletRequestAWS.getRemoteHost().equals("127.0.0.1")) {
            msg = msg + " | root cause >>> " + stackTraceElements[0];
        }
        return ResponseHandler.output(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                msg, null
        );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> throwDuplicateException(Exception ex, HttpServletRequest request) {
        String msg = Optional.ofNullable(ex.getMessage())
                .orElse("");
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        int idxParenthesis = msg.indexOf("(");

        if (idxParenthesis != -1) msg = msg.substring(0, idxParenthesis).trim();
        msg = msg.replace("\"", "");
        return ResponseHandler.output(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                "Data being requested is exist", null
        );
    }
}