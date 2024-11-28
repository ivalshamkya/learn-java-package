package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseReligionOrganization;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Religion Organization API")
@RequestMapping("/religion")
@DependsOn("religionOrganizationHandlerMap")
public class ReligionOrganizationController {
    private final Map<String, BaseReligionOrganization<?>> baseReligionOrganizationMap;
    
    public ReligionOrganizationController(
        @Qualifier("religionOrganizationHandlerMap") Map<String, BaseReligionOrganization<?>> baseReligionOrganizationHandlerMap
    ) {
        this.baseReligionOrganizationMap = baseReligionOrganizationHandlerMap;
    }
    
    private BaseReligionOrganization<?> getHandler() { return baseReligionOrganizationMap.get(ServletRequestAWS.getSourceApp()); }
    
    @GetMapping
    public ResponseEntity<Object> getReligionsOrganization(@Valid @RequestParam Map<Object, Object> param) throws Exception {
        Object result = getHandler().getReligionsOrganizations(param);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
}
