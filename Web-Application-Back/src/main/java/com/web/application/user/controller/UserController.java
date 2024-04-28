package com.web.application.user.controller;

import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.user.dto.request.UserPasswordChangeRequest;
import com.web.application.user.dto.request.UserProjectUpdateRequest;
import com.web.application.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/mobile/wfm/users")
public class UserController {

    private final UserService userService;

    // 마이페이지에서 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<?> userPasswordChange(@RequestBody @Valid UserPasswordChangeRequest requestDTO,
                                                @AuthenticationPrincipal Long userSeq){

        userService.userPasswordChange(userSeq, requestDTO);

        return ResponseEntity
                .ok()
                .build();
    }

    // 프로젝트 선택 - 회사 목록 불러오기
    @GetMapping("/customers")
    public ResponseEntity<?> getCustomerOptions(@AuthenticationPrincipal TokenInfo tokenInfo){

        return  ResponseEntity
                .ok()
                .body(userService.getCustomerOptions(tokenInfo));
    }

    // 프로젝트 선택 - 회사코드로 프로젝트(+법인)목록 불러오기
    @GetMapping("/projects")
    public ResponseEntity<?> getProjectOptions(@AuthenticationPrincipal TokenInfo tokenInfo,
                                               @RequestParam(defaultValue = "") String customerCode){
        return ResponseEntity
                .ok()
                .body(userService.getProjectOptions(tokenInfo, customerCode));
    }

    // 프로젝트 선택 변경
    @PatchMapping("/projects")
    public ResponseEntity<?> updateUserProject(@AuthenticationPrincipal TokenInfo tokenInfo,
                                               @RequestBody @Valid UserProjectUpdateRequest request){

        userService.updateUserProject(tokenInfo, request);

        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal TokenInfo tokenInfo){

        userService.deleteUser(tokenInfo);

        return ResponseEntity
                .ok()
                .build();
    }
}
