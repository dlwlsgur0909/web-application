package com.web.application.auth.service;

import com.web.application.auth.dto.request.JoinRequest;
import com.web.application.auth.dto.request.LoginRequest;
import com.web.application.auth.dto.response.LoginResponse;
import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.auth.repository.AuthRepository;
import com.web.application.member.entity.Member;
import com.web.application.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void join(final JoinRequest request) {

        String username = request.getUsername();
        String nickname = request.getNickname();

        if(isDuplicateUsername(username)) {
            throw new RuntimeException("Duplicate Username");
        }

        if(isDuplicateNickname(nickname)) {
            throw new RuntimeException("Duplicate Nickname");
        }

        Member targetMember = request.toEntity();
        String encodedPassword = passwordEncoder.encode(targetMember.getPassword());
        targetMember.changePassword(encodedPassword);

        Member savedMember = authRepository.save(targetMember);
        savedMember.setAuditor();

        authRepository.save(savedMember);
    }

    public LoginResponse login(final LoginRequest request) {

        String username = request.getUsername();
        String rawPassword = request.getPassword();

        Member targetMember = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

        if(!passwordEncoder.matches(rawPassword, targetMember.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        TokenInfo tokenInfo = new TokenInfo(targetMember);

        String accessToken = jwtUtil.createAccessToken(tokenInfo);
        String refreshToken = jwtUtil.createRefreshToken(tokenInfo);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .nickname(tokenInfo.getNickname())
                .build();
    }


    private Boolean isDuplicateUsername(final String username) {

        return authRepository.existsByUsername(username);
    }

    private Boolean isDuplicateNickname(final String nickname) {

        return authRepository.existsByNickname(nickname);
    }

}
