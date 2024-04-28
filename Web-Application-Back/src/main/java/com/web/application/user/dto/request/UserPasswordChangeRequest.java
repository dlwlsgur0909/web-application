package com.web.application.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPasswordChangeRequest {

    @NotBlank
    private String currentPassword;
    @NotBlank
    @Size(min = 10)
    private String newPassword;

}
