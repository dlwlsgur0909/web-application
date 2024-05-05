package com.web.application.auth.repository;

import com.web.application.member.entity.Member;
import org.springframework.data.repository.ListCrudRepository;

public interface AuthRepository extends ListCrudRepository<Member, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByNickname(String nickname);



}
