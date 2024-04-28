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
        encryptor.setPassword("GoodCenWorkforceManagement");

        String username = "econnet";
        String password = "econnet1234#$";
        String secret = "vmfhaltmskdlstkfkdgodyroqkfwkdbalroqkfwkdbazzzzcnaaaaccscsaaaabbbbb";
        String ERPToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBUElVU0VSPT09Iiwicm9sZXMiOltdLCJpYXQiOjE3MTMyMjkyMTQsImV4cCI6MjM0Mzk0OTIxNH0.GZa7CfeV3vJAlITzw8nzrWxvJ7jjshyPTixngH0_cbc";

        // when
        String encUsername = encryptor.encrypt(username);
        String encPassword = encryptor.encrypt(password);
        String encSecret = encryptor.encrypt(secret);
        String encERPToken = encryptor.encrypt(ERPToken);

        System.out.println("username: ENC(" + encUsername + ")");
        System.out.println("password: ENC(" + encPassword + ")");
        System.out.println("secert: ENC(" + encSecret + ")");
        System.out.println("ERPToken: ENC(" + encERPToken + ")");

        // then
        Assertions.assertEquals(encryptor.decrypt(encUsername), username);
        Assertions.assertEquals(encryptor.decrypt(encPassword), password);
        Assertions.assertEquals(encryptor.decrypt(encSecret), secret);
        Assertions.assertEquals(encryptor.decrypt(encERPToken), ERPToken);

    }



}