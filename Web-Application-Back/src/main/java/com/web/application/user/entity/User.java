package com.web.application.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    private Long userSeq;
    private Long employeeNumber;
    private String userNameKr;
    private String userNameEn;
    private String userMobileNumber;
    private String userPassword;
    private String currentRegisterCode;
    private String insertId;
    private LocalDateTime insertDate;
    private String updateId;
    private LocalDateTime updateDate;


    // 사용자 비밀번호 update
    public void updateUserPassword(String encodedPassword){
        this.userPassword = encodedPassword;
    }

    // 사용자 회사선택 update
    public void updateUserProject(String updateRegisterCode){
        this.currentRegisterCode = updateRegisterCode;
    }
}
