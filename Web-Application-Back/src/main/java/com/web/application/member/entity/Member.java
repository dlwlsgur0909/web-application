package com.web.application.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    private Long memberId;
    private String username;
    private String password;
    private String nickname;
    private String memberName;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime modifiedAt;
    private Long modifiedBy;


    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void setAuditor() {
        this.createdBy = memberId;
        this.modifiedBy = memberId;
    }
}
