package com.web.application.auth.dto.response;

import com.web.application.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenInfo {

    private String username;
    private String nickname;

    public TokenInfo(Member entity) {
        this.username = entity.getUsername();
        this.nickname = entity.getNickname();
    }

}
