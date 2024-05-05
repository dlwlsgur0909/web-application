package com.web.application.auth.service;

import com.web.application.auth.dto.request.JoinRequest;
import com.web.application.auth.repository.AuthRepository;
import com.web.application.member.entity.Member;
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


    private Boolean isDuplicateUsername(final String username) {

        return authRepository.existsByUsername(username);
    }

    private Boolean isDuplicateNickname(final String nickname) {

        return authRepository.existsByNickname(nickname);
    }

}
