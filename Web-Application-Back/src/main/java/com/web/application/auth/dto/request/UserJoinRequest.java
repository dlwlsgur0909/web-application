package com.web.application.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJoinRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String userSSN;
    @NotBlank
    private String userMobileNumber;
    @NotBlank
    private String userPassword;

}
