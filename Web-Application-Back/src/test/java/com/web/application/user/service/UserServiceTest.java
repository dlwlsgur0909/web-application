package com.web.application.user.service;

import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.user.dto.request.UserPasswordChangeRequest;
import com.web.application.user.dto.request.UserProjectUpdateRequest;
import com.web.application.user.entity.User;
import com.web.application.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("사용자 비밀번호 변경 TEST")
    void userPasswordChange() {

        User targetUser = User.builder()
                .employeeNumber(1234L)
                .userNameKr("테스트김유림")
                .userNameEn("testKYL")
                .userMobileNumber("01011111111")
                .userPassword(passwordEncoder.encode("testPassword123"))
                .currentRegisterCode("2210202")
                .insertId("test")
                .insertDate(LocalDateTime.now())
                .build();

        userRepository.save(targetUser);

        UserPasswordChangeRequest requestDTO = new UserPasswordChangeRequest("testPassword123", "newTestPassword123!");
        userService.userPasswordChange(targetUser.getUserSeq(), requestDTO);

    }

    @Test
    @DisplayName("사용자 프로젝트 선택 변경 TEST")
    void updateUserProject(){
        User targetUser = User.builder()
                .employeeNumber(10000000L)
                .userNameKr("테스트김유림")
                .userNameEn("testKYL")
                .userMobileNumber("01011111111")
                .userPassword(passwordEncoder.encode("testPassword123"))
                .currentRegisterCode("2210202")
                .insertId("test")
                .insertDate(LocalDateTime.now())
                .build();
        userRepository.save(targetUser);

        TokenInfo targetToken = new TokenInfo(targetUser);

        UserProjectUpdateRequest updateData = new UserProjectUpdateRequest("1234");
        userService.updateUserProject(targetToken, updateData);
    }

    @Test
    @DisplayName("사용자 계정 탈퇴(삭제) TEST")
    void deleteUser(){
        User targetUser = User.builder()
                .employeeNumber(10000001L)
                .userNameKr("테스트김유림")
                .userNameEn("testKYL")
                .userMobileNumber("01011111111")
                .userPassword(passwordEncoder.encode("testPassword123"))
                .currentRegisterCode("2210202")
                .insertId("test")
                .insertDate(LocalDateTime.now())
                .build();
        // 저장 후 삭제
        userRepository.save(targetUser);
        TokenInfo targetToken = new TokenInfo(targetUser);

        System.out.println("targetToken = " + targetToken.getUserSeq());
        userService.deleteUser(targetToken);
    }
}