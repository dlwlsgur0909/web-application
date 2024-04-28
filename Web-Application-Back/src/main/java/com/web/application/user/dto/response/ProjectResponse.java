package com.web.application.user.dto.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private String registerCode;
    private String corporationCode;
    private String corporationName;
    private String projectCode;
    private String projectName;

}
