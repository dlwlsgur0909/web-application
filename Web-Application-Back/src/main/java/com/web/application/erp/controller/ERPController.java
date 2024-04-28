package com.web.application.erp.controller;

import com.web.application.erp.service.ERPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mobile/wfm/erp")
@Slf4j
public class ERPController {

    private final ERPService userProjectService;

    @GetMapping
    public ResponseEntity<?> apiTest() {

        log.info("API CALLED - apiTest()");

        return ResponseEntity
                .ok()
                .body("OK");
    }

    @PostMapping
    public ResponseEntity<?> saveUserProjectViaERP(@RequestBody Map<String, Object> params) {

        log.info("API CALLED - saveUserProjectViaERP()");

        userProjectService.saveUserProjectViaERP(params);

        return ResponseEntity
                .ok()
                .body("Y");
    }

}
