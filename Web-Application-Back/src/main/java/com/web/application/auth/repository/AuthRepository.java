package com.web.application.auth.repository;

import com.web.application.member.entity.Member;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AuthRepository extends ListCrudRepository<Member, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByNickname(String nickname);


    Optional<Member> findByUsername(String username);
}
