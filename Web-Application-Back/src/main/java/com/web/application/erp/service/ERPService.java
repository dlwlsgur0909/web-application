package com.web.application.erp.service;

import com.web.application.erp.dto.response.ERPResponse;
import com.web.application.erp.repository.ERPRepository;
import com.web.application.user.entity.UserProject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ERPService {

    private final ERPRepository erpRepository;
    private final WebClient webClient;

    @Transactional
    public void saveUserProjectViaERP(final Map<String, Object> params) {

        String procedure = "DSZPR_MOBILE_USER_PROJECT_IF";

        Map<String, Object> newParams = new HashMap<>();
        newParams.put("type", "S");
        newParams.put("key", procedure);
        newParams.put("params", params);

        ERPResponse response = webClient.post()
                .uri("/api/exec")
                .body(Mono.just(newParams), Map.class)
                .retrieve()
                .bodyToMono(ERPResponse.class)
                .block();

        log.info("SP - {}, code : {}, httpStatus : {}, message : {}",
                procedure, response.getCode(), response.getHttpStatus(), response.getMessage()
        );

        List<UserProject> userProjectList = response.getResult().stream()
                .map(this::convertMapToUserProject)
                .toList();

        erpRepository.saveAll(userProjectList);
    }


    private UserProject convertMapToUserProject(Map map) {

        return UserProject.builder()
                .employeeNumber(Long.valueOf(String.valueOf(map.get("EMPLOYEE_NUMBER"))))
                .registerCode(String.valueOf(map.get("REGISTER_CODE")))
                .customerCode(String.valueOf(map.get("CUSTOMER_CODE")))
                .customerName(String.valueOf(map.get("CUSTOMER_NAME")))
                .corporationCode(String.valueOf(map.get("CORPORATION_CODE")))
                .corporationName(String.valueOf(map.get("CORPORATION_NAME")))
                .projectCode(String.valueOf(map.get("PROJECT_CODE")))
                .projectName(String.valueOf(map.get("PROJECT_NAME")))
                .projectAddress(String.valueOf(map.get("PROJECT_ADDRESS")))
                .contractBeginDate(String.valueOf(map.get("CONTRACT_BEGIN_DATE")))
                .contractEndDate(String.valueOf(map.get("CONTRACT_END_DATE")))
                .insertId(String.valueOf(map.get("INSERT_ID")))
                .insertDate(LocalDateTime.now())
                .build();

    }

}
