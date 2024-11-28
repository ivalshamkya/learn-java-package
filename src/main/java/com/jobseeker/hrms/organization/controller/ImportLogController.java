package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseImportLog;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jobseeker.enums.general.MessageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Import Log API")
@RequestMapping("/import-log")
@DependsOn("importLogHandlerMap")
public class ImportLogController {
    private final Map<String, BaseImportLog<?>> importLogHandlerMap;

    public ImportLogController(@Qualifier("importLogHandlerMap") Map<String, BaseImportLog<?>> importLogHandlerMap) {
        this.importLogHandlerMap = importLogHandlerMap;
    }

    private BaseImportLog<?> getHandler() {
        return importLogHandlerMap.get(ServletRequestAWS.getSourceApp());
    }

    @GetMapping
    public ResponseEntity<Object> getImportLogs(@RequestParam @Valid Map<Object, Object> param) {
        Page<?> results = getHandler().getImportLogs(param);

        return ResponseHandler.output(
                HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), results);
    }
}
