package com.web.application.auth.controller;

import com.web.application.auth.dto.request.AuthNameRequest;
import com.web.application.auth.dto.request.AuthPhoneRequest;
import com.web.application.auth.dto.request.LoginRequest;
import com.web.application.auth.dto.request.UserJoinRequest;
import com.web.application.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/mobile/wfm/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/name")
    public ResponseEntity<?> authenticateName(@RequestBody @Valid AuthNameRequest requestDTO) {

        String tempName = requestDTO.getUserName();
        String tempNumber = requestDTO.getNumber();

        boolean isValid = false;

        if(tempName.startsWith("test") && tempNumber.startsWith("test")) {
            isValid = true;
        }

        return ResponseEntity
                .ok()
                .body(isValid);
    }

    @PostMapping("/phone")
    public ResponseEntity<?> authenticatePhone(@RequestBody @Valid AuthPhoneRequest requestDTO) {

        String tempName = requestDTO.getUserName();
        String tempDOB = requestDTO.getDateOfBirth();
        String tempAgency = requestDTO.getAgency();
        String tempMobileNumber = requestDTO.getMobileNumber();

        boolean isValid = false;

        if (tempName.startsWith("test") && tempDOB.startsWith("test") && tempAgency.startsWith("test") && tempMobileNumber.startsWith("test")) {
            isValid = true;
        }

        return ResponseEntity
                .ok()
                .body(isValid);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserJoinRequest requestDTO) {

        authService.join(requestDTO);

        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest requestDTO) {

        return ResponseEntity
                .ok()
                .body(authService.login(requestDTO));
    }


}
