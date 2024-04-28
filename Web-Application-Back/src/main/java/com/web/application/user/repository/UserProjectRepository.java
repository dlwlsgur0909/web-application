package com.web.application.user.repository;

import com.web.application.user.entity.UserProject;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserProjectRepository extends ListCrudRepository<UserProject, Long> {

    // 해당 근로자 번호를 갖고있는 회사 목록 get
    List<UserProject> findByEmployeeNumber(Long employeeNumber);

    // 해당 근로자 번호를 갖고있는 회사에 대한 프로젝트 목록 get
    List<UserProject> findByEmployeeNumberAndCustomerCode(Long employeeNumber, String customerCode);
}
