package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseHardSkill;
import com.jobseeker.hrms.organization.service.baseContract.BaseJobLevel;
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
@Tag(name = "Hard Skill API")
@RequestMapping("/hard-skill")
@DependsOn("hardSkillHandlerMap")
public class HardSkillController {
    private final Map<String, BaseHardSkill<?>> hardSkillHandlerMap;
    
    public HardSkillController(@Qualifier("hardSkillHandlerMap") Map<String, BaseHardSkill<?>> hardSkillHandlerMap) {
        this.hardSkillHandlerMap = hardSkillHandlerMap;
    }
    
    private BaseHardSkill<?> getHandler() { return hardSkillHandlerMap.get(ServletRequestAWS.getSourceApp()); }
    
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Map<Object, Object> request) throws Exception {
        Object result = getHandler().createHardSkill(request);
        
        return ResponseHandler.output(HttpStatus.CREATED.value(), HttpStatus.CREATED,
            MessageResponse.MSG_SUCCESS_SAVE_DATA.getMessage(),result);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) throws Exception {
        String message  = getHandler().deleteHardSkill(id);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            message, null);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Map<Object, Object> request) throws Exception {
        Object result = getHandler().updateHardSkill(request, id);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_UPDATE_DATA.getMessage(),result);
    }
    
    @GetMapping
    public ResponseEntity<Object> getHardSkills(@Valid @RequestParam Map<Object, Object> param) throws Exception {
        Object result = getHandler().getHardSkills(param);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getHardSkill(@PathVariable String id) throws Exception {
        Object result = getHandler().getHardSkill(id);
        
        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
            MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }
}
