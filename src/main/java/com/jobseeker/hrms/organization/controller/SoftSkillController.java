package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseSoftSkill;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.Map;

@RestController
@Tag(name = "Soft Skill API")
@RequestMapping("/soft-skill")
@DependsOn("softSkillHandlerMap")
public class SoftSkillController {
    private final Map<String, BaseSoftSkill<?>> softSkillHandlerMap;
    
    public SoftSkillController(
        @Qualifier("softSkillHandlerMap") Map<String, BaseSoftSkill<?>> softSkillHandlerMap
    ) {
        this.softSkillHandlerMap = softSkillHandlerMap;
    }
    
    private BaseSoftSkill<?> getHandler() { return softSkillHandlerMap.get(ServletRequestAWS.getSourceApp()); }
    
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
        Object result = getHandler().createSoftSkill(request);
        
        return ResponseHandler.output(HttpStatus.CREATED.value(), HttpStatus.CREATED,
            MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(), result);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Map<Object, Object> request, @PathVariable("id") String id) throws Exception {
        Object result = getHandler().updateSoftSkill(request, id);
        
        return  ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(), result);
    }
    
    @DeleteExchange("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) throws Exception {
        String result = getHandler().deleteSoftSkill(id);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            result, null);
    }
    
    @GetMapping
    public ResponseEntity<Object> getSoftSkills(@Valid @RequestParam Map<Object, Object> param) throws Exception {
        Object result = getHandler().getSoftSkills(param);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSoftSkill(@PathVariable String id) throws Exception {
        Object result = getHandler().getSoftSkill(id);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
}
