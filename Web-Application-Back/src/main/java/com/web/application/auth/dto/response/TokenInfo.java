package com.web.application.auth.dto.response;

import com.web.application.user.entity.User;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenInfo {

    private Long userSeq;
    private Long employeeNumber;
    private String currentRegisterCode;
    private String role;

    public TokenInfo(User user) {
        this.userSeq = user.getUserSeq();
        this.employeeNumber = user.getEmployeeNumber();
        this.currentRegisterCode = user.getCurrentRegisterCode();
        this.role = "USER";
    }
}
