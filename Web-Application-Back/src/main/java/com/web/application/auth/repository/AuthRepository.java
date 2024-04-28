package com.web.application.auth.repository;

import com.web.application.user.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AuthRepository extends ListCrudRepository<User, Long> {
    Optional<User> findByUserMobileNumber(String userMobileNumber);

    Boolean existsByEmployeeNumberOrUserMobileNumber(Long employeeNumber, String userMobileNumber);
}
