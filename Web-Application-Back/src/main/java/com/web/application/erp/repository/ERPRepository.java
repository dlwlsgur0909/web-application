package com.web.application.erp.repository;

import com.web.application.user.entity.UserProject;
import org.springframework.data.repository.ListCrudRepository;

public interface ERPRepository extends ListCrudRepository<UserProject, Long> {
}
