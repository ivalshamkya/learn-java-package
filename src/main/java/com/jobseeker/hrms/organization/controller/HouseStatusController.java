package com.jobseeker.hrms.organization.controller;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.ResponseHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseHouseStatus;
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
@Tag(name = "House Status API")
@RequestMapping("/house-status")
@DependsOn("houseStatusHandlerMap")
public class HouseStatusController {
    private final Map<String, BaseHouseStatus<?>> houseStatusHandlerMap;

    public HouseStatusController(@Qualifier("houseStatusHandlerMap") Map<String, BaseHouseStatus<?>> houseStatusHandlerMap) {
        this.houseStatusHandlerMap = houseStatusHandlerMap;
    }

    private BaseHouseStatus<?> getHandler() {
        return houseStatusHandlerMap.get(ServletRequestAWS.getSourceApp());
    }

    @GetMapping
    public ResponseEntity<Object> getHouseStatuses(@Valid @RequestParam Map<Object, Object> request) throws Exception {
        Object result = getHandler().getHouseStatuses(request);

        return ResponseHandler.output(HttpStatus.OK.value(), HttpStatus.OK,
                MessageResponse.MSG_SUCCESS_GET_DATA.getMessage(), result);
    }

}
