package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseReasonOrganization;
import com.jobseeker.hrms.organization.service.baseContract.BaseSoftSkill;
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
@Tag(name = "Reason Organization API")
@RequestMapping("/reason")
@DependsOn("reasonOrganizationHandlerMap")
public class ReasonOrganizationController {
    private final Map<String, BaseReasonOrganization<?>> baseOrganizationHandlerMap;
    
    public ReasonOrganizationController(
        @Qualifier("reasonOrganizationHandlerMap") Map<String, BaseReasonOrganization<?>> baseOrganizationHandlerMap
    ) {
        this.baseOrganizationHandlerMap = baseOrganizationHandlerMap;
    }
    
    private BaseReasonOrganization<?> getHandler() { return baseOrganizationHandlerMap.get(ServletRequestAWS.getSourceApp()); }
    
    @GetMapping
    public ResponseEntity<Object> getReasonOrganizations(@Valid @RequestParam Map<Object, Object> param) throws Exception {
        Object result = getHandler().getDataReasons(param);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
}
