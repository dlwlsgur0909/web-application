package com.web.application.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {

    @Test
    @DisplayName("데이터베이스 username, password, Jwt Secret 데이터를 암호화 합니다")
    void jasyptTest() {

        // given
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("myapp");

        String username = "root";
        String password = "admin";
        String secret = "vmfhaltmskdlstkfkdgodyroqkfwkdbalroqkfwkdbazzzzcnaaaaccscsaaaabbbbb";

        // when
        String encUsername = encryptor.encrypt(username);
        String encPassword = encryptor.encrypt(password);
        String encSecret = encryptor.encrypt(secret);

        System.out.println("username: ENC(" + encUsername + ")");
        System.out.println("password: ENC(" + encPassword + ")");
        System.out.println("secert: ENC(" + encSecret + ")");

        // then
        Assertions.assertEquals(encryptor.decrypt(encUsername), username);
        Assertions.assertEquals(encryptor.decrypt(encPassword), password);
        Assertions.assertEquals(encryptor.decrypt(encSecret), secret);

    }



}