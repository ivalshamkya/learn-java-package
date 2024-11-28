package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Filter API")
@RequestMapping("/filter")
@DependsOn("filterHandlerMap")
public class FilterController {

    private final Map<String, BaseFilter<?>> baseFilter;

    public FilterController(@Qualifier("filterHandlerMap") Map<String, BaseFilter<?>> baseFilter) {
        this.baseFilter = baseFilter;
    }

    private BaseFilter<?> getHandler() {
        return baseFilter.get(ServletRequestAWS.getSourceApp());
    }

    @GetMapping("/department-active-vacancy")
    public ResponseEntity<Object> getDepartmentActiveVacancy(
    ) {
        var responseData = getHandler().getDepartmentHasActiveVacancy();
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), responseData
        );
    }

    @GetMapping("/city-active-vacancy")
    public ResponseEntity<Object> getItems() {
        var responseData = getHandler().getCityHasActiveVacancy();
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), responseData
        );
    }

    @GetMapping("/work-area-active-vacancy")
    public ResponseEntity<Object> getWorkArea() {
        var responseData = getHandler().getWorkAreaHasActiveVacancy();
        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), responseData
        );
    }
}
