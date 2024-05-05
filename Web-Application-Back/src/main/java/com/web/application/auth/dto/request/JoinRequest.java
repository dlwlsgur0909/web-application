package com.web.application.auth.dto.request;

import com.web.application.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String memberName;

    public Member toEntity() {

        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .memberName(memberName)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }



}
