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
public class AuthPhoneRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String agency;
    @NotBlank
    private String mobileNumber;

}
