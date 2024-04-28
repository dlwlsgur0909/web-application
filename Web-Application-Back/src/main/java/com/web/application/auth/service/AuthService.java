package com.web.application.auth.service;

import com.web.application.auth.dto.request.LoginRequest;
import com.web.application.auth.dto.request.UserJoinRequest;
import com.web.application.auth.dto.response.LoginResponse;
import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.auth.repository.AuthRepository;
import com.web.application.user.entity.User;
import com.web.application.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final WebClient webClient;

    private final JwtUtil jwtUtil;

    public void join(final UserJoinRequest requestDTO) {

        String procedure = "이름, 주민번호, 전화번호로 근로자번호 받아오는 SP명";

        String userName = requestDTO.getUserName();
        String userSSN = requestDTO.getUserSSN();
        String userMobileNumber = requestDTO.getUserMobileNumber();
        String userPassword = requestDTO.getUserPassword();

        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("userSSN", userSSN);
        params.put("userMobileNumber", userMobileNumber);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "S");
        map.put("key", procedure);
        map.put("params", params);

        // SP 확인 후 적용 예정 (영문 이름 관련 협의 필요)
//        ERPResponseDTO response = webClient.post()
//                .uri("/api/exec")
//                .body(Mono.just(map), Map.class)
//                .retrieve()
//                .bodyToMono(ERPResponseDTO.class)
//                .block();

        /**
         * 중복확인
         * 할당된 프로젝트 있는지 확인
         * 개인정보 약관 동의
         */

        String encodedPassword = passwordEncoder.encode(userPassword);

        /**
         * 회원 저장
         * - InsertId 확인
         * - 프로젝트 코드 확인
         */



    }

    public LoginResponse login(final LoginRequest requestDTO) {

        String userMobileNumber = requestDTO.getUserMobileNumber();
        String userPassword = requestDTO.getUserPassword();

        User targetUser = authRepository.findByUserMobileNumber(userMobileNumber)
                .orElseThrow(() -> new BadCredentialsException("Invalid ID"));

        if(!passwordEncoder.matches(userPassword, targetUser.getUserPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        TokenInfo tokenInfo = new TokenInfo(targetUser);

        String accessToken = jwtUtil.createAccessToken(tokenInfo);
        String refreshToken = jwtUtil.createRefreshToken(tokenInfo);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Boolean duplicateCheck(final Long employeeNumber, final String userMobileNumber) {

        return authRepository.existsByEmployeeNumberOrUserMobileNumber(employeeNumber, userMobileNumber);
    }

}
