package com.web.application.home.controller;

import com.web.application.auth.dto.response.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<?> home(@AuthenticationPrincipal TokenInfo tokenInfo) {

        return ResponseEntity
                .ok()
                .body(tokenInfo.getNickname());
    }

}
