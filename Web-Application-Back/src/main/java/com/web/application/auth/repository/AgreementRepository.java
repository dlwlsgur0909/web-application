package com.web.application.auth.repository;

import com.web.application.auth.entity.Agreement;
import org.springframework.data.repository.ListCrudRepository;

public interface AgreementRepository extends ListCrudRepository<Agreement, Long> {
}
