package com.web.application.auth.service;

import com.web.application.auth.repository.AuthRepository;
import com.web.application.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    AuthRepository authRepository;

    @Test
    @DisplayName("근로자번호, 핸드폰번호 중복 시 회원가입에 실패해야 한다")
    void duplicateUserTest() {

        // given
        User testUser = User.builder()
                .employeeNumber(10001L)
                .userMobileNumber("01012345678")
                .userNameKr("테스트근로자")
                .userNameEn("TestWorker")
                .userPassword("test")
                .currentRegisterCode("TEST-SITE")
                .insertId("test")
                .insertDate(LocalDateTime.now())
                .build();

        authRepository.save(testUser);

        Long dupEmployeeNumber = 10001L;
        String dupMobileNumber = "01012345678";

        // when
        Boolean duplicate = authRepository.existsByEmployeeNumberOrUserMobileNumber(dupEmployeeNumber, dupMobileNumber);

        // then
        Assertions.assertThat(duplicate).isTrue();

    }

}