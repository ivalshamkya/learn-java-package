package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseSATNationality;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "SAT Nationality API")
@RequestMapping("/nationality")
@DependsOn("nationalityHandlerMap")
public class NationalityController {
    private final Map<String, BaseSATNationality<?>> nationalityHandlerMap;
    
    public NationalityController(
        @Qualifier("nationalityHandlerMap") Map<String, BaseSATNationality<?>> nationalityHandlerMap
    ) {
        this.nationalityHandlerMap = nationalityHandlerMap;
    }
    
    private BaseSATNationality<?> getHandler() { return nationalityHandlerMap.get(ServletRequestAWS.getSourceApp()); }
    
    @GetMapping
    public ResponseEntity<Object> getNationalities(@Valid @RequestParam Map<Object, Object> param) throws Exception {
        Object result = getHandler().getSATNationalities(param);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
    
}
