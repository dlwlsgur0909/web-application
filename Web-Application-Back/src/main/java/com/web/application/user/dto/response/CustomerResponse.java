package com.web.application.user.dto.response;

import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "customerCode")
public class CustomerResponse {

    private String customerCode;
    private String customerName;

}
