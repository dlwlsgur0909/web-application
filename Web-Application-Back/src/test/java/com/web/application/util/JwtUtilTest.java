package com.web.application.util;

import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.user.entity.User;
import com.web.application.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Test
    @DisplayName("ERP용 토큰 발급")
    void test() {
        String erpToken = jwtUtil.createERPToken();
        Date expiration = jwtUtil.getExpiration(erpToken);

        System.out.println("erpToken = " + erpToken);
        System.out.println("expiration = " + expiration);
    }

    @Test
    void userTest(){
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

        TokenInfo targetToken = new TokenInfo(targetUser);
        String accessToken = jwtUtil.createAccessToken(targetToken);
        String refreshToken = jwtUtil.createRefreshToken(targetToken);

        System.out.println("accessToken = " + accessToken);
        System.out.println("refreshToken = " + refreshToken);
    }

}
