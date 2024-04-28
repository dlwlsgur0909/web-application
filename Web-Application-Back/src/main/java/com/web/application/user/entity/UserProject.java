package com.web.application.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class UserProject {

    @Id
    private Long userProjectSeq;
    private Long employeeNumber;
    private String registerCode;
    private String customerCode;
    private String customerName;
    private String corporationCode;
    private String corporationName;
    private String projectCode;
    private String projectName;
    private String projectAddress;
    private String contractBeginDate;
    private String contractEndDate;
    private String insertId;
    private LocalDateTime insertDate;
    private String updateId;
    private LocalDateTime updateDate;
}
