package com.web.application.erp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ERPResponse {

    private Integer code;
    private String httpStatus;
    private String success;
    private String message;
    private int count;
    private List<Map> result = new ArrayList<>();
}
